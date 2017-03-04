package com.powerhouse.interview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Profile;
import com.powerhouse.interview.service.BusinessDelegate;
import com.powerhouse.interview.util.Converter;
import com.powerhouse.interview.util.FileUtil;

@Controller
@RequestMapping("/")
public class ConsumptionFileController {

	@Autowired
	BusinessDelegate businessDelegate;

	private FileUtil fileUtil = new FileUtil();
	private Converter converter = new Converter();

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/profileUpload", method = RequestMethod.POST)
	public String handleProfileUpload(@RequestParam("datafile") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (isFileNameSuffixCsv(file.getOriginalFilename())) {
			try {
				List<String> profiles = fileUtil.readLines(file);
				Collection<Profile> successfullyCreatedProfiles = businessDelegate.handleProfiles(profiles);
				flashSuccessfullyCreatedProfiles(redirectAttributes, "profileMessage", successfullyCreatedProfiles);
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("profileMessage", e.getMessage());
			} catch (BusinessFault e) {
				redirectAttributes.addFlashAttribute("profileMessage", e.getMessage());
			}

		} else {
			redirectAttributes.addFlashAttribute("profileMessage", "Excepted file type is csv. You choosed " + file.getOriginalFilename() + "!");
		}
		
		redirectAttributes.addFlashAttribute("profilesInDB", businessDelegate.fetchProfiles());
		redirectAttributes.addFlashAttribute("metersInDB", businessDelegate.fetchMeterReadings());

		return "redirect:/";
	}

	private void flashSuccessfullyCreatedProfiles(RedirectAttributes redirectAttributes, String attributeName, Collection<Profile> profiles) {
		List<Profile> successfullyCreatedProfiles = new ArrayList<Profile>();
		for(Profile profile : profiles) {
			Profile currentProfileInDB = businessDelegate.getProfile(profile.getName());
			if(currentProfileInDB != null) {
				successfullyCreatedProfiles.add(currentProfileInDB);
			}						
		}
		redirectAttributes.addFlashAttribute(attributeName, successfullyCreatedProfiles);
	}

	public boolean isFileNameSuffixCsv(String fileName) {
		if (fileName != null) {
			String[] splittedFileName = fileName.split("\\.");
			if ("csv".equals(splittedFileName[splittedFileName.length - 1])) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "/meterUpload", method = RequestMethod.POST)
	public String handleMeterReadingsUpload(@RequestParam("datafile") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (isFileNameSuffixCsv(file.getOriginalFilename())) {
			try {
				List<String> meterReadings = fileUtil.readLines(file);
				removeHeaderForMeterReadings(meterReadings);
				Collection<MeterReading> inputMeterReadingMap = converter.convertToMeterReadingsFromCommaSeperatedStrings(meterReadings);
				List<MeterReading> recordedMeterReadings = new ArrayList<MeterReading>();
				List<String> violationExceptions = new ArrayList<>();
				handleMeterReadings(inputMeterReadingMap, recordedMeterReadings, violationExceptions);
				logRecordedReadings(redirectAttributes, recordedMeterReadings);
				logViolations(redirectAttributes, violationExceptions);
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("meterReadingMessage", e.getMessage());
			} catch (BusinessFault e) {
				redirectAttributes.addFlashAttribute("meterReadingMessage", e.getMessage());
			}

		} else {
			redirectAttributes.addFlashAttribute("meterReadingMessage", "Excepted file type is csv. You choosed " + file.getOriginalFilename() + "!");
		}
		
		redirectAttributes.addFlashAttribute("profilesInDB", businessDelegate.fetchProfiles());
		redirectAttributes.addFlashAttribute("metersInDB", businessDelegate.fetchMeterReadings());

		return "redirect:/";
	}
	
	public void removeHeaderForMeterReadings(List<String> meterReadings) {
		if (!meterReadings.isEmpty()) {
			String firstLine = meterReadings.get(0);
			String[] arr = firstLine.split(",");
			if (arr != null && arr.length > 0) {
				try {
					Integer.parseInt(arr[0]);
				} catch (NumberFormatException e) {
					meterReadings.remove(0);
				}
			}
		}
	}
	
	public void handleMeterReadings(Collection<MeterReading> inputMeterReadings, List<MeterReading> recordedMeterReadings,
			List<String> violationExceptions) {
		for(MeterReading meterReading : inputMeterReadings) {
			try {
				businessDelegate.validateMeterReading(meterReading);
				businessDelegate.recordMeterReading(meterReading);
				recordedMeterReadings.add(meterReading);
			} catch (BusinessFault e) {
				violationExceptions.add(e.getMessage());
			}
		}
	}

	private void logViolations(RedirectAttributes redirectAttributes, List<String> violationExceptions) {
		if(!violationExceptions.isEmpty()) {					
			redirectAttributes.addFlashAttribute("meterReadingViolations", violationExceptions);
		}
	}

	private void logRecordedReadings(RedirectAttributes redirectAttributes, List<MeterReading> recordedMeterReadings) {
		if(!recordedMeterReadings.isEmpty()) {					
			redirectAttributes.addFlashAttribute("meterReadingMessage", recordedMeterReadings);
		}
	}

}
