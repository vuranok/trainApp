package com.powerhouse.interview.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.powerhouse.interview.BusinessFault;
import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Profile;
import com.powerhouse.interview.entity.Response;

public class Converter {
	
	public Collection<Profile> convertToProfileFromCommaSeperatedStrings(List<String> inputList) throws BusinessFault {
		
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
		
		return map.values();
	}

	public Collection<MeterReading> convertToMeterReadingsFromCommaSeperatedStrings(List<String> meterReadings) throws BusinessFault {
		
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
		
		return map.values();
	}
	
	public Map<Integer, MeterReading> convertToMeterReadingsMap(List<MeterReading> meterReadings) {
		
		Map<Integer, MeterReading> map = new HashMap<Integer, MeterReading>();

		for(MeterReading meterReading : meterReadings) {
			map.put(meterReading.getMeterID(), meterReading);
		}
		
		return map;
	}

	public String profilesToJsonResponse(ArrayList<Profile> profiles) {
		Response response = new Response();
		response.setDescription("The following profiles were successfully recorded.");
		
		for(Profile profile : profiles) {
			response.getProfileNames().add(profile.getName());
		}
		
		Gson gson = new Gson();
		return gson.toJson(response, Response.class);
	}
	
}
