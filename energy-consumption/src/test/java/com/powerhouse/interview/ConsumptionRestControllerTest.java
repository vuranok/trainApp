package com.powerhouse.interview;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.service.BusinessDelegate;
import com.powerhouse.interview.util.Converter;
import com.powerhouse.interview.util.MeterReadingBuilder;

public class ConsumptionRestControllerTest {

	@InjectMocks
	private ConsumptionRestController classUnderTest;

	@Mock
	private Converter mockedConverter;

	@Mock
	private BusinessDelegate businessDelegate;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void recordedMeterIdAndViolationMessageMustExistInJsonResult() throws BusinessFault {
		ArrayList<MeterReading> meterReadings = new ArrayList<MeterReading>();
		MeterReading meterReading1 = MeterReadingBuilder.aMeterReading().withMeterId(1).build();
		MeterReading meterReading2 = MeterReadingBuilder.aMeterReading().withMeterId(2).build();
		meterReadings.add(meterReading1);
		meterReadings.add(meterReading2);

		Converter converter = new Converter();
		classUnderTest.setConverter(converter);

		String message = "test";
		doThrow(new BusinessFault(message)).when(businessDelegate).validateMeterReading(meterReading2);

		classUnderTest.recordMeterReadings(meterReadings);

		assertEquals("{\"recordedMeterIds\":[" + meterReading1.getMeterID() + "],\"meterReadingsViolations\":[\"" + message + "\"]}",
				classUnderTest.recordMeterReadings(meterReadings));
	}

	@Test(expected=IllegalArgumentException.class)
	public void anIllegalArgumentExceptionMustBeThrownWhenGivenMonthIsNotReadible() throws BusinessFault {
		classUnderTest.getMeterReadings(3, "jan");
	}
	
}
