package com.powerhouse.interview.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.powerhouse.interview.entity.BusinessFault;
import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Month;
import com.powerhouse.interview.entity.Profile;
import com.powerhouse.interview.util.Converter;
import com.powerhouse.interview.util.FileUtil;
import com.powerhouse.interview.util.Validate;

@Controller
public class BusinessDelegate {

	@Autowired
	private ProfileService profileService;

	private FileUtil fileUtil = new FileUtil();
	private Validate validate = new Validate();
	private Converter converter = new Converter();
	
	public void handleProfile(MultipartFile file) throws IOException, BusinessFault {

		List<String> profiles = fileUtil.readLines(file);
		removeHeaderForProfile(profiles);
		Map<String, Profile> inputProfileMap = converter.convertToProfileMap(profiles);
		validateProfiles(inputProfileMap);
		profileService.persistProfileMap(inputProfileMap);
	}

	public void removeHeaderForProfile(List<String> profiles) {
		if (!profiles.isEmpty()) {
			String firstLine = profiles.get(0);
			if (firstLine.length() > 3 && Month.getValue(firstLine.substring(0, 3).toUpperCase()) == null) {				
				profiles.remove(0);
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
	
	public Profile getProfile(String key) {
		return profileService.getProfile(key);
	}

	public void handleMeterReadings(MultipartFile file) throws IOException, BusinessFault {

		List<String> meterReadings = fileUtil.readLines(file);
		removeHeaderForProfile(meterReadings);
		Map<Integer, MeterReading> inputMeterReadingMap = converter.convertToMeterReadingMap(meterReadings);
		validateMeterReadings(inputMeterReadingMap);
		profileService.persistMeterReadingMap(inputMeterReadingMap);
	}

	private String validateMeterReadings(Map<Integer, MeterReading> inputMeterReadingMap) throws BusinessFault {
		StringBuilder builder = new StringBuilder();
		for (MeterReading meterReading : inputMeterReadingMap.values()) {
			if (!validate.meterReading(meterReading.getMeterReadingMap())) {
				builder.append("For a given meter a reading for a month should not be lower than the previous one. Meter id is ");
				builder.append(meterReading.getMeterID());
				builder.append("\n");
				continue;
			}
			Profile currentProfile = profileService.getProfile(meterReading.getProfileName());
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
		return builder.toString();
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

}
