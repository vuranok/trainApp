package com.powerhouse.interview.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.powerhouse.interview.entity.BusinessFault;
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
	
	public Map<String, Profile> handleProfiles(List<String> profiles) throws IOException, BusinessFault {

		removeHeaderForProfile(profiles);
		Map<String, Profile> inputProfileMap = converter.convertToProfileMap(profiles);
		validateProfiles(inputProfileMap);
		service.persistProfileMap(inputProfileMap);
		return inputProfileMap;
	}
	
	public Map<Integer, MeterReading> handleMeterReadings(List<String> meterReadings) throws IOException, BusinessFault {

		removeHeaderForMeterReadings(meterReadings);
		Map<Integer, MeterReading> inputMeterReadingMap = converter.convertToMeterReadingMap(meterReadings);
		validateMeterReadings(inputMeterReadingMap);
		service.persistMeterReadingMap(inputMeterReadingMap);
		return inputMeterReadingMap;
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

	public void removeHeaderForMeterReadings(List<String> meterReadings) {
		if (!meterReadings.isEmpty()) {
			String firstLine = meterReadings.get(0);
			String[] arr = firstLine.split(",");
			if (arr != null && arr.length > 0) {
				try {
					Integer.parseInt(arr[0]);
				} catch (NumberFormatException e) {
					meterReadings.remove(0);
				}
			}
		}
	}
	
	private void validateProfiles(Map<String, Profile> givenProfileMap) throws BusinessFault {
		for (Profile profile : givenProfileMap.values()) {
			if (!validate.fractions(profile)) {
				throw new BusinessFault("Given fractions are not valid for profile " + profile.getName());
			}
		}
	}

	private void validateMeterReadings(Map<Integer, MeterReading> inputMeterReadingMap) throws BusinessFault {
		StringBuilder builder = new StringBuilder();
		for (MeterReading meterReading : inputMeterReadingMap.values()) {
			if (!validate.meterReading(meterReading.getMeterReadingMap())) {
				builder.append("For a given meter a reading for a month should not be lower than the previous one. Meter id is ");
				builder.append(meterReading.getMeterID());
				builder.append("\n");
				continue;
			}
			Profile currentProfile = service.getProfile(meterReading.getProfileName());
			if(currentProfile == null) {
				builder.append("Fractions for the profiles contained in the data should exist. Meter id is ");
				builder.append(meterReading.getMeterID());
				builder.append(" profile name is ");
				builder.append(meterReading.getProfileName());
				builder.append("\n");
				continue;
			}
			if(!validate.consumptions(meterReading.getMeterReadingMap(), currentProfile.getFractionMap())) {
				builder.append("Consumption violation exist. Meter id is ");
				builder.append(meterReading.getMeterID());
				builder.append(" profile name is ");
				builder.append(meterReading.getProfileName());
				builder.append("\n");
				continue;
			}
		}
		if(!builder.toString().isEmpty()) {			
			throw new BusinessFault(builder.toString());
		}
		
	}

	public List<Profile> fetchProfiles() {
		
		return service.fetchProfiles();
	}

	public List<MeterReading> fetchMeters() {
		
		return service.fetchMeters();
	}

}
