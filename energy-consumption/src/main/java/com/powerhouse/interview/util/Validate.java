package com.powerhouse.interview.util;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.powerhouse.interview.entity.Profile;

@Configuration
public class Validate {

	@Bean
	public Validate getInstance() {
		return new Validate();
	}
	
	public boolean fileNameSuffix(String fileName) {
		if(fileName != null) {
			String[] splittedFileName = fileName.split("\\.");
			if("csv".equals(splittedFileName[splittedFileName.length - 1])) {
				return true;
			}
		}
		return false;
	}

	public boolean fractions(Profile profile) {
		double sum = 0d;
		
		for(double fraction : profile.getFractionMap().values()) {
			sum += fraction;
		}
		
		return sum == 1d;
	}
	
}
