package com.powerhouse.interview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.powerhouse.interview.entity.BusinessFault;
import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Profile;
import com.powerhouse.interview.service.BusinessDelegate;
import com.powerhouse.interview.util.FileUtil;

@Controller
@RequestMapping("/")
public class ConsumptionFileController {

	@Autowired
	BusinessDelegate businessDelegate;

	private FileUtil fileUtil = new FileUtil();

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/profileUpload", method = RequestMethod.POST)
	public String handleProfileUpload(@RequestParam("datafile") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (isFileNameSuffixCsv(file.getOriginalFilename())) {
			try {
				List<String> profiles = fileUtil.readLines(file);
				Map<String, Profile> successfullyCreatedProfileMap = businessDelegate.handleProfiles(profiles);
				addSuccessfullyCreatedProfilesAttribute(redirectAttributes, "profileMessage", successfullyCreatedProfileMap.values());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("profileMessage", e.getMessage());
			} catch (BusinessFault e) {
				redirectAttributes.addFlashAttribute("profileMessage", e.getMessage());
			}

		} else {
			redirectAttributes.addFlashAttribute("profileMessage", "Excepted file type is csv. You choosed " + file.getOriginalFilename() + "!");
		}
		
		redirectAttributes.addFlashAttribute("profilesInDB", businessDelegate.fetchProfiles());
		redirectAttributes.addFlashAttribute("metersInDB", businessDelegate.fetchMeters());

		return "redirect:/";
	}

	private void addSuccessfullyCreatedProfilesAttribute(RedirectAttributes redirectAttributes, String attributeName, Collection<Profile> profiles) {
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
				Map<Integer, MeterReading> successfullyCreatedMeterReadingsMap = businessDelegate.handleMeterReadings(meterReadings);
				addSuccessfullyCreatedMeterReadingsAttribute(redirectAttributes, "meterReadingMessage", successfullyCreatedMeterReadingsMap.values());
				redirectAttributes.addFlashAttribute("meterReadingMessage", businessDelegate.getMeterReading(1) + "\n" + businessDelegate.getProfile("B"));
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("meterReadingMessage", e.getMessage());
			} catch (BusinessFault e) {
				redirectAttributes.addFlashAttribute("meterReadingMessage", e.getMessage());
			}

		} else {
			redirectAttributes.addFlashAttribute("meterReadingMessage", "Excepted file type is csv. You choosed " + file.getOriginalFilename() + "!");
		}
		
		redirectAttributes.addFlashAttribute("profilesInDB", businessDelegate.fetchProfiles());
		redirectAttributes.addFlashAttribute("metersInDB", businessDelegate.fetchMeters());

		return "redirect:/";
	}

	private void addSuccessfullyCreatedMeterReadingsAttribute(RedirectAttributes redirectAttributes, String attributeName, Collection<MeterReading> values) {
		List<MeterReading> successfullyCreatedMeterReadings = new ArrayList<MeterReading>();
		for(MeterReading meterReading : values) {
			MeterReading currentMeterReadingInDB = businessDelegate.getMeterReading(meterReading.getMeterID());
			if(currentMeterReadingInDB != null) {
				successfullyCreatedMeterReadings.add(currentMeterReadingInDB);
			}						
		}
		redirectAttributes.addFlashAttribute(attributeName, successfullyCreatedMeterReadings);
	}

}
