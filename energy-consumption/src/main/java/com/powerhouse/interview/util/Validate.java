package com.powerhouse.interview.util;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.powerhouse.interview.entity.Month;
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

	public boolean meterReading(Map<Month, Integer> map) {
		if(map.get(Month.JANUARY) > map.get(Month.FEBRUARY) ||
				map.get(Month.FEBRUARY) > map.get(Month.MARCH) ||
				map.get(Month.MARCH) > map.get(Month.APRIL) ||
				map.get(Month.APRIL) > map.get(Month.MAY) ||
				map.get(Month.MAY) > map.get(Month.JUNE) ||
				map.get(Month.JUNE) > map.get(Month.JULY) ||
				map.get(Month.JULY) > map.get(Month.AUGUST) ||
				map.get(Month.AUGUST) > map.get(Month.SEPTEMBER) ||
				map.get(Month.SEPTEMBER) > map.get(Month.OCTOBER) ||
				map.get(Month.OCTOBER) > map.get(Month.NOVEMBER) ||
				map.get(Month.NOVEMBER) > map.get(Month.DECEMBER)) {
			return false;
		}
		return true;
	}

	public boolean consumptions(Map<Month, Integer> meterReadingMap, Map<Month, Double> fractionMap) {
		
		Integer[] readings = new Integer[12];
		Double[] fractions = new Double[12];
		
		meterReadingMap.values().toArray(readings);
		fractionMap.values().toArray(fractions);

		BigDecimal totalConsumption = getTotalConsumption(readings);
		
		boolean valid = validateConsumption(totalConsumption, fractions[0], readings[0]);
		if(!valid) {
			return valid;
		}
		
		for(int i = 1; i < 12; i++) {
			double fraction = fractions[i];
			int consumption = readings[i] - readings[i - 1];
			valid = validateConsumption(totalConsumption, fraction, consumption);
			if(!valid) {
				return valid;
			}
		}
		
		return true;
	}

	private boolean validateConsumption(BigDecimal totalConsumption, double fraction, int consumption) {
		BigDecimal result = totalConsumption.multiply(BigDecimal.valueOf(fraction));
		BigDecimal tolerance = result.multiply(new BigDecimal(0.25));
		BigDecimal minValue = result.subtract(tolerance);
		BigDecimal maxValue = result.add(tolerance);
		
		if(minValue.intValue() > consumption || consumption > maxValue.intValue()) {			
			return false;
		}
		return true;
	}
	
	private BigDecimal getTotalConsumption(Integer[] readings) {
		int total = 0;
		for(Integer i : readings) {
			total += i;
		}
		return new BigDecimal(total);
	}
	
}
