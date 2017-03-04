package com.powerhouse.interview.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.powerhouse.interview.BusinessFault;
import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Month;
import com.powerhouse.interview.util.MeterReadingBuilder;

public class BusinessDelegateTest {

	@InjectMocks
	private BusinessDelegate classUnderTest;

	@Mock
	private BusinessService businessService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
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
	public void consumptionForJanuaryMustBeTheSameWithMeterReadingOfJanuary() throws BusinessFault {
		Integer meterId = 1;
		Double januaryReading = 9d;
		MeterReading meterReading = MeterReadingBuilder.aMeterReading().withMeterId(meterId).withJanuaryReading(januaryReading).build();

		when(businessService.getMeterReading(meterId)).thenReturn(meterReading);
		assertEquals(januaryReading, classUnderTest.calculateConsumption(meterId, Month.JANUARY));
	}
	
	@Test
	public void consumptionForAMonthMustBeTheMinusOfThePreviousMonthReading() throws BusinessFault {
		Integer meterId = 1;
		Double januaryReading = 9.2d;
		Double februaryReading = 12.6d;
		Double consumption = 3.4d;
		MeterReading meterReading = MeterReadingBuilder.aMeterReading().withMeterId(meterId).withJanuaryReading(januaryReading).withFebruaryReading(februaryReading).build();

		when(businessService.getMeterReading(meterId)).thenReturn(meterReading);
		assertEquals(consumption, classUnderTest.calculateConsumption(meterId, Month.FEBRUARY));
	}
	
}
