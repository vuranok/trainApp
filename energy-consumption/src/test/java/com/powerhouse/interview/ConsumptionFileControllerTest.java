package com.powerhouse.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ConsumptionFileControllerTest {

	ConsumptionFileController controller;
	
	@Before
	public void setup() {
		controller = new ConsumptionFileController();
	}
	
	@Test
	public void profileFileNameSuffixMustBeCsv() {
		String fileName = "profile.csv";
		
		assertTrue(controller.isFileNameSuffixCsv(fileName));
	}

	@Test
	public void suffixViolationWhenProfileFileNameSuffixIsOtherThanCsv() {
		String fileName = "profile.jpg";
		
		assertFalse(controller.isFileNameSuffixCsv(fileName));
	}
	
	@Test
	public void testRemoveHeaderForMeterReadings() {
		List<String> meterReadings = new ArrayList<String>();
		meterReadings.add("MeterID,Profile,Month,Meter reading");
		meterReadings.add("0001,A,JAN,10");
		
		controller.removeHeaderForMeterReadings(meterReadings);
		
		assertEquals(1, meterReadings.size());
	}
	
	@Test
	public void ifFirstRowIsAboutMeterReadingThenItMustNotBeRemoved() {
		List<String> meterReadings = new ArrayList<String>();
		meterReadings.add("0001,A,JAN,10");
		
		controller.removeHeaderForMeterReadings(meterReadings);
		
		assertEquals(1, meterReadings.size());
	}
}
