package com.powerhouse.interview;

import java.util.ArrayList;
import java.util.Collection;
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
import com.powerhouse.interview.service.FileBusinessDelegate;
import com.powerhouse.interview.util.Converter;

@RestController
@RequestMapping(value="/energy")
public class ConsumptionRestController {

	@Autowired
	FileBusinessDelegate businessDelegate;
	
	private Converter converter = new Converter();
	
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
		Collection<MeterReading> recordedMeterReadings = null;

		recordedMeterReadings = businessDelegate.recordMeterReadings(meterReadings);

		if (recordedMeterReadings != null) {
			return "Followiıng meter readings are recorded.\n" + recordedMeterReadings.toString();
		}
		return "No any meterReading was recorded.";
	}
	
	@RequestMapping(value = "/meters", method = RequestMethod.GET)
	public @ResponseBody List<MeterReading> getMeterReadings() {
		return businessDelegate.fetchMeterReadings();
	}
	
	@RequestMapping(value = "/consumption", method = RequestMethod.GET)
	public String getMeterReadings(Integer meterId, String month) {
		Month monthEnum;
		try {
			monthEnum = Month.valueOf(month.toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("Given month should be like one of the followings ignoring case "
					+ "january, february, march, april, may, june, july, august, september, october, november, december");
		}

		return meterId + " " + month;
	}
	
}
