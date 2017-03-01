package com.powerhouse.interview.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.powerhouse.interview.BusinessFault;
import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Month;
import com.powerhouse.interview.entity.Profile;
import com.powerhouse.interview.util.Converter;
import com.powerhouse.interview.util.Validate;

@Controller
public class BusinessDelegate {

	@Autowired
	private BusinessService service;
	
	private Validate validate = new Validate();
	private Converter converter = new Converter();
	
	public Collection<Profile> handleProfiles(List<String> profiles) throws IOException, BusinessFault {

		removeHeaderForProfile(profiles);
		Collection<Profile> inputProfiles = converter.convertToProfilesFromCommaSeperatedStrings(profiles);
		validateProfiles(inputProfiles);
		service.persistProfiles(inputProfiles);
		return inputProfiles;
	}
	
	public Profile getProfile(String key) {
		return service.getProfile(key);
	}
	
	public MeterReading getMeterReading(Integer id) {
		return service.getMeterReading(id);
	} 

	public void removeHeaderForProfile(List<String> profiles) {
		if (!profiles.isEmpty()) {
			String firstLine = profiles.get(0);
			if (firstLine.length() > 3 && Month.getValue(firstLine.substring(0, 3).toUpperCase()) == null) {				
				profiles.remove(0);
			}
		}
	}

	public List<Profile> fetchProfiles() {
		
		return service.fetchProfiles();
	}

	public List<MeterReading> fetchMeterReadings() {
		
		return service.fetchMeterReadings();
	}

	public void recordProfiles(List<Profile> profiles) throws BusinessFault {

		validateProfiles(profiles);
		service.persistProfiles(profiles);
	}
	
	private void validateProfiles(Collection<Profile> givenProfiles) throws BusinessFault {
		for (Profile profile : givenProfiles) {
			Map<Month, Double> fractionMap = profile.getFractionMap();

			if (!validate.isAllMonthsExist(fractionMap.keySet()) || !validate.fractions(profile)) {
				throw new BusinessFault("Given fractions are not valid for profile " + profile.getName());
			}
		}
	}

	public void validateMeterReading(MeterReading meterReading) throws BusinessFault {
		StringBuilder builder = new StringBuilder();
				
		if (!validate.meterReading(meterReading.getMeterReadingMap())) { 
			builder.append("For a given meter a reading for a month should not be lower than the previous one. Meter id is ");
			builder.append(meterReading.getMeterID());
			builder.append(". ");
			throw new BusinessFault(builder.toString());
		}
		Profile currentProfile = service.getProfile(meterReading.getProfileName());
		if(currentProfile == null) {
			builder.append("Fractions for the profiles contained in the data should exist. Meter id is ");
			builder.append(meterReading.getMeterID());
			builder.append(" profile name is ");
			builder.append(meterReading.getProfileName());
			builder.append(". ");
			throw new BusinessFault(builder.toString());
		}
		if(!validate.consumptions(meterReading.getMeterReadingMap(), currentProfile.getFractionMap())) {
			builder.append("Consumption violation exist. Meter id is ");
			builder.append(meterReading.getMeterID());
			builder.append(" profile name is ");
			builder.append(meterReading.getProfileName());
			builder.append(". ");
			throw new BusinessFault(builder.toString());
		}
	}

	public void recordMeterReading(MeterReading meterReading) {
		service.persistMeterReadingMap(meterReading);
	}

	public Integer calculateConsumption(Integer meterId, Month monthEnum) throws BusinessFault {
		MeterReading meterReading = service.getMeterReading(meterId);
		if(meterReading == null) {
			throw new BusinessFault("No records exist for given meter id " + meterId);
		}
		
		Integer inputMonthReading = meterReading.getMeterReadingMap().get(monthEnum);
		Integer previousMonthReading = 0;
		if(!monthEnum.equals(Month.JANUARY)) {			
			Month previousMonth = Month.getPreviousMonth(monthEnum);
			previousMonthReading = meterReading.getMeterReadingMap().get(previousMonth);
		}
		
		return inputMonthReading - previousMonthReading;
	}

}
