package com.powerhouse.interview.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;
import java.util.Set;

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
		BigDecimal sum = new BigDecimal(0d, MathContext.DECIMAL32);
		
		Map<Month, Double> fractionMap = profile.getFractionMap();
		
		for(Double fraction : fractionMap.values()) {
			sum = sum.add(new BigDecimal(fraction, MathContext.DECIMAL32));
		}
		
		return sum.compareTo(new BigDecimal(1d)) == 0;
	}

	public boolean isAllMonthsExist(Set<Month> monthSet) {
		if(!monthSet.contains(Month.JANUARY) || 
				!monthSet.contains(Month.FEBRUARY) || 
				!monthSet.contains(Month.MARCH) || 
				!monthSet.contains(Month.APRIL) || 
				!monthSet.contains(Month.MAY) || 
				!monthSet.contains(Month.JUNE) || 
				!monthSet.contains(Month.JULY) || 
				!monthSet.contains(Month.AUGUST) || 
				!monthSet.contains(Month.SEPTEMBER) || 
				!monthSet.contains(Month.OCTOBER) || 
				!monthSet.contains(Month.NOVEMBER) || 
				!monthSet.contains(Month.DECEMBER)) {
			return false;
		}
		return true;
	}

	public boolean meterReading(Map<Month, Double> map) {
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

	public boolean consumptions(Map<Month, Double> meterReadingMap, Map<Month, Double> fractionMap) {
		
		Double[] readings = new Double[12];
		Double[] fractions = new Double[12];
		
		meterReadingMap.values().toArray(readings);
		fractionMap.values().toArray(fractions);

		BigDecimal totalConsumption = getTotalConsumption(readings);
		
		boolean valid = validateConsumption(totalConsumption, BigDecimal.valueOf(fractions[0]), BigDecimal.valueOf(readings[0]));
		if(!valid) {
			return valid;
		}
		
		for(int i = 1; i < 12; i++) {
			BigDecimal fraction = BigDecimal.valueOf(fractions[i]);
			BigDecimal consumption = BigDecimal.valueOf(readings[i]).subtract(BigDecimal.valueOf(readings[i - 1]));
			valid = validateConsumption(totalConsumption, fraction, consumption);
			if(!valid) {
				return valid;
			}
		}
		
		return true;
	}

	private boolean validateConsumption(BigDecimal totalConsumption, BigDecimal fraction, BigDecimal consumption) {
		BigDecimal result = totalConsumption.multiply(fraction);
		BigDecimal tolerance = result.multiply(BigDecimal.valueOf(0.25));
		BigDecimal minValue = result.subtract(tolerance);
		BigDecimal maxValue = result.add(tolerance);
		
		if(minValue.compareTo(consumption) > 0 || maxValue.compareTo(consumption) < 0) {			
			return false;
		}
		return true;
	}
	
	private BigDecimal getTotalConsumption(Double[] readings) {
		BigDecimal total = BigDecimal.ZERO;
		for(Double i : readings) {
			total = total.add(new BigDecimal(i));
		}
		return total;
	}
	
}
