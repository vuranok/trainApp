package com.powerhouse.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.service.BusinessDelegate;
import com.powerhouse.interview.util.MeterReadingBuilder;

public class ConsumptionFileControllerTest {

	@InjectMocks
	ConsumptionFileController classUnderTest;

	@Mock
	private BusinessDelegate businessDelegate;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void profileFileNameSuffixMustBeCsv() {
		String fileName = "profile.csv";
		
		assertTrue(classUnderTest.isFileNameSuffixCsv(fileName));
	}

	@Test
	public void suffixViolationWhenProfileFileNameSuffixIsOtherThanCsv() {
		String fileName = "profile.jpg";
		
		assertFalse(classUnderTest.isFileNameSuffixCsv(fileName));
	}
	
	@Test
	public void testRemoveHeaderForMeterReadings() {
		List<String> meterReadings = new ArrayList<String>();
		meterReadings.add("MeterID,Profile,Month,Meter reading");
		meterReadings.add("0001,A,JAN,10");
		
		classUnderTest.removeHeaderForMeterReadings(meterReadings);
		
		assertEquals(1, meterReadings.size());
	}
	
	@Test
	public void ifFirstRowIsAboutMeterReadingThenItMustNotBeRemoved() {
		List<String> meterReadings = new ArrayList<String>();
		meterReadings.add("0001,A,JAN,10");
		
		classUnderTest.removeHeaderForMeterReadings(meterReadings);
		
		assertEquals(1, meterReadings.size());
	}
	
	@Test
	public void recordedMeterIdAndViolationMessageMustExistInJsonResult() throws BusinessFault {
		ArrayList<MeterReading> meterReadings = new ArrayList<MeterReading>();
		MeterReading meterReading1 = MeterReadingBuilder.aMeterReading().withMeterId(1).build();
		MeterReading meterReading2 = MeterReadingBuilder.aMeterReading().withMeterId(2).build();
		
		meterReadings.add(meterReading1);
		meterReadings.add(meterReading2);

		String message = "test";
		doThrow(new BusinessFault(message)).when(businessDelegate).validateMeterReading(meterReading2);

		List<MeterReading> recordedMeterReading = new ArrayList<>();
		List<String> violationExceptions = new ArrayList<>();
		classUnderTest.handleMeterReadings(meterReadings, recordedMeterReading, violationExceptions);

		assertEquals(1, recordedMeterReading.size());
		assertEquals(meterReading1.getMeterID(), recordedMeterReading.get(0).getMeterID());
		assertEquals(1, violationExceptions.size());
		assertEquals(message, violationExceptions.get(0));
	}
	
}
