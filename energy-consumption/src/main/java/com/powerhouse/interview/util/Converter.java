package com.powerhouse.interview.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.powerhouse.interview.entity.BusinessFault;
import com.powerhouse.interview.entity.Profile;

@Configuration
public class Converter {

	@Bean
	public Converter getInstance() {
		return new Converter();
	}
	
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
}
