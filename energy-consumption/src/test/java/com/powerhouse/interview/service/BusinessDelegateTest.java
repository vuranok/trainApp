package com.powerhouse.interview.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BusinessDelegateTest {

	@Test
	public void testRemoveHeaderForProfile() {
		BusinessDelegate businessDelegate = new BusinessDelegate();
		List<String> profiles = new ArrayList<String>();
		profiles.add("Month,Profile,Fraction");
		profiles.add("JAN,A,0.15");
		
		businessDelegate.removeHeaderForProfile(profiles);
		
		assertEquals(1, profiles.size());
	}
	
	@Test
	public void ifFirstRowIsAboutMonthThenItMustNotBeRemoved() {
		BusinessDelegate businessDelegate = new BusinessDelegate();
		List<String> profiles = new ArrayList<String>();
		profiles.add("JAN,A,0.15");
		
		businessDelegate.removeHeaderForProfile(profiles);
		
		assertEquals(1, profiles.size());
	}

	@Test
	public void testRemoveHeaderForMeterReadings() {
		BusinessDelegate businessDelegate = new BusinessDelegate();
		List<String> meterReadings = new ArrayList<String>();
		meterReadings.add("MeterID,Profile,Month,Meter reading");
		meterReadings.add("0001,A,JAN,10");
		
		businessDelegate.removeHeaderForMeterReadings(meterReadings);
		
		assertEquals(1, meterReadings.size());
	}
	
	@Test
	public void ifFirstRowIsAboutMeterReadingThenItMustNotBeRemoved() {
		BusinessDelegate businessDelegate = new BusinessDelegate();
		List<String> meterReadings = new ArrayList<String>();
		meterReadings.add("0001,A,JAN,10");
		
		businessDelegate.removeHeaderForMeterReadings(meterReadings);
		
		assertEquals(1, meterReadings.size());
	}

}
