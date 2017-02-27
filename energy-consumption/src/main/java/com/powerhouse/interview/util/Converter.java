package com.powerhouse.interview.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powerhouse.interview.BusinessFault;
import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Profile;

public class Converter {
	
	public Map<String, Profile> convertToProfileMap(List<String> inputList) throws BusinessFault {
		
		Map<String, Profile> map = new HashMap<String, Profile>();

		for(String line : inputList) {			
			String[] parts = line.split(",");
			if(parts.length != 3) {				
				throw new BusinessFault("A comma must be used as a line seperator");
			}
			
			if(map.containsKey(parts[1])) {
				Profile currentProfile = map.get(parts[1]);
				currentProfile.addFraction(parts);
			}
			else {
				Profile currentProfile = new Profile(parts[1]);
				currentProfile.addFraction(parts);
				map.put(parts[1], currentProfile);
			}
		}
		
		return map;
	}

	public Map<Integer, MeterReading> convertToMeterReadingMap(List<String> meterReadings) throws BusinessFault {
		
		Map<Integer, MeterReading> map = new HashMap<Integer, MeterReading>();

		for(String line : meterReadings) {			
			String[] parts = line.split(",");
			if(parts.length != 4) {				
				throw new BusinessFault("A comma must be used as a line seperator");
			}
			
			Integer meterId;
			try {
				meterId = Integer.parseInt(parts[0]);
			} catch (NumberFormatException  e) {
				throw new BusinessFault("Given MeterID must be an integer!");
			}
			
			if(map.containsKey(meterId)) {
				MeterReading currentMeterReading = map.get(meterId);
				currentMeterReading.addReading(parts);
			}
			else {
				MeterReading currentMeterReading = new MeterReading(meterId, parts[1]);
				currentMeterReading.addReading(parts);
				map.put(meterId, currentMeterReading);
			}
		}
		
		return map;
	}

	public Map<String, Profile> convertToMap(List<Profile> profiles) {
		
		Map<String, Profile> map = new HashMap<String, Profile>();

		for(Profile profile : profiles) {			
			map.put(profile.getName(), profile);
		}
		
		return map;
	}
}
