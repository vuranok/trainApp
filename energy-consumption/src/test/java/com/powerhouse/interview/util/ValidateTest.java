package com.powerhouse.interview.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.powerhouse.interview.BusinessFault;
import com.powerhouse.interview.entity.Month;
import com.powerhouse.interview.entity.MonthComparator;
import com.powerhouse.interview.entity.Profile;

public class ValidateTest {
	
	Validate validate;
	
	@Before
	public void setup() {
		validate = new Validate();
	}
	
	@Test
	public void forAProfileTheSumOfAllFractionsShouldBe1() throws BusinessFault {
		Profile profile = ProfileBuilder.aProfile().withName("A").withSumOfFractionsOne(true).build();
		
		assertTrue(validate.fractions(profile));
	}
	
	@Test
	public void validationMustBeFalseWhenSumOfFractionsAreNot1() throws BusinessFault {
		Profile profile = ProfileBuilder.aProfile().withName("A").withSumOfFractionsOne(false).build();
		
		assertFalse(validate.fractions(profile));
	}
	
	@Test
	public void consumptionsShouldBeConsistenWithFractions() {
		
		Map<Month, Integer> meterReadingMap = new TreeMap<Month, Integer>(new MonthComparator());
		meterReadingMap.put(Month.JANUARY, 0);
		meterReadingMap.put(Month.FEBRUARY, 0);
		meterReadingMap.put(Month.MARCH, 0);
		meterReadingMap.put(Month.APRIL, 0);
		meterReadingMap.put(Month.MAY, 0);
		meterReadingMap.put(Month.JUNE, 0);
		meterReadingMap.put(Month.JULY, 0);
		meterReadingMap.put(Month.AUGUST, 0);
		meterReadingMap.put(Month.SEPTEMBER, 0);
		meterReadingMap.put(Month.OCTOBER, 0);
		meterReadingMap.put(Month.NOVEMBER, 0);
		meterReadingMap.put(Month.DECEMBER, 10);
		
		Map<Month, Double> fractionMap = new TreeMap<Month, Double>(new MonthComparator());
		fractionMap.put(Month.JANUARY, 0.0);
		fractionMap.put(Month.FEBRUARY, 0.0);
		fractionMap.put(Month.MARCH, 0.0);
		fractionMap.put(Month.APRIL, 0.0);
		fractionMap.put(Month.MAY, 0.0);
		fractionMap.put(Month.JUNE, 0.0);
		fractionMap.put(Month.JULY, 0.0);
		fractionMap.put(Month.AUGUST, 0.0);
		fractionMap.put(Month.SEPTEMBER, 0.0);
		fractionMap.put(Month.OCTOBER, 0.0);
		fractionMap.put(Month.NOVEMBER, 0.0);
		fractionMap.put(Month.DECEMBER, 1.0);
		
		assertTrue(validate.consumptions(meterReadingMap, fractionMap));
	}
	
	@Test
	public void meterReadingsCanNotBeLowerThanPreviousMonth() {
		Map<Month, Integer> map = new HashMap<Month, Integer>();
		
		map.put(Month.JANUARY, 10);
		map.put(Month.FEBRUARY, 9);
		map.put(Month.MARCH, 9);
		map.put(Month.APRIL, 9);
		map.put(Month.MAY, 9);
		map.put(Month.JUNE, 9);
		map.put(Month.JULY, 9);
		map.put(Month.AUGUST, 9);
		map.put(Month.SEPTEMBER, 9);
		map.put(Month.OCTOBER, 9);
		map.put(Month.NOVEMBER, 9);
		map.put(Month.DECEMBER, 9);
		assertFalse(validate.meterReading(map));
		
		map.put(Month.FEBRUARY, 10);
		assertFalse(validate.meterReading(map));
		
		map.put(Month.MARCH, 10);
		assertFalse(validate.meterReading(map));
		
		map.put(Month.APRIL, 10);
		assertFalse(validate.meterReading(map));
		
		map.put(Month.MAY, 10);
		assertFalse(validate.meterReading(map));
		
		map.put(Month.JUNE, 10);
		assertFalse(validate.meterReading(map));
		
		map.put(Month.JULY, 10);
		assertFalse(validate.meterReading(map));
		
		map.put(Month.AUGUST, 10);
		assertFalse(validate.meterReading(map));
		
		map.put(Month.SEPTEMBER, 10);
		assertFalse(validate.meterReading(map));
		
		map.put(Month.OCTOBER, 10);
		assertFalse(validate.meterReading(map));
		
		map.put(Month.NOVEMBER, 10);
		assertFalse(validate.meterReading(map));
		
	}
}
 