package com.powerhouse.interview;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Month;
import com.powerhouse.interview.entity.Profile;
import com.powerhouse.interview.service.BusinessDelegate;
import com.powerhouse.interview.util.Converter;

@RestController
@RequestMapping(value="/energy")
public class ConsumptionRestController {

	@Autowired
	BusinessDelegate businessDelegate;
	
	@Autowired
	private Converter converter;
	
	@RequestMapping(value = "/profiles", method = RequestMethod.POST)
	public String recordProfiles(@RequestBody ArrayList<Profile> profiles) throws BusinessFault {
		businessDelegate.recordProfiles(profiles);
		
		return converter.profilesToJsonResponse(profiles);
	}
	
	@RequestMapping(value = "/profiles", method = RequestMethod.GET)
	public @ResponseBody List<Profile> getProfiles() {
		return businessDelegate.fetchProfiles();
	}
	
	@RequestMapping(value = "/meters", method = RequestMethod.POST)
	public String recordMeterReadings(@RequestBody ArrayList<MeterReading> meterReadings) throws BusinessFault {
		List<MeterReading> recordedMeterReadings = new ArrayList<MeterReading>();
		List<String> violationExceptions = new ArrayList<>();
		
		for(MeterReading meterReading : meterReadings) {
			try {
				businessDelegate.validateMeterReading(meterReading);
				businessDelegate.recordMeterReading(meterReading);
				recordedMeterReadings.add(meterReading);
			} catch (BusinessFault e) {
				violationExceptions.add(e.getMessage());
			}
		}

		return converter.meterReadingsToJsonResponse(recordedMeterReadings, violationExceptions);
	}
	
	@RequestMapping(value = "/meters", method = RequestMethod.GET)
	public @ResponseBody List<MeterReading> getMeterReadings() {
		return businessDelegate.fetchMeterReadings();
	}
	
	@RequestMapping(value = "/consumption", method = RequestMethod.GET)
	public String getMeterReadings(Integer meterId, String month) throws BusinessFault {
		Month monthEnum;
		try {
			monthEnum = Month.valueOf(month.toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("Given month must be in the following list (ignoring case) "
					+ "january, february, march, april, may, june, july, august, september, october, november, december");
		}

		Double consumption = businessDelegate.calculateConsumption(meterId, monthEnum);
		return converter.consumptionToJsonResponse(meterId, monthEnum, consumption);
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	}
	
}
