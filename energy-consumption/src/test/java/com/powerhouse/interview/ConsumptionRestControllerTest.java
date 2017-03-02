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
		MeterReading meterReading1 = new MeterReading();
		meterReading1.setMeterID(1);
		MeterReading meterReading2 = new MeterReading();
		meterReading2.setMeterID(2);
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

}
