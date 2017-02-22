package com.powerhouse.interview.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.powerhouse.interview.entity.Profile;

@Configuration
public class Validate {

	@Bean
	public Validate getInstance() {
		return new Validate();
	}

	public boolean fractions(Profile profile) {
		double sum = 0d;
		
		for(double fraction : profile.getFractionMap().values()) {
			sum += fraction;
		}
		
		return sum == 1d;
	}
	
}
