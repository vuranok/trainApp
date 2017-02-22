package com.powerhouse.interview.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.powerhouse.interview.entity.BusinessFault;
import com.powerhouse.interview.entity.Month;
import com.powerhouse.interview.entity.Profile;
import com.powerhouse.interview.util.Converter;
import com.powerhouse.interview.util.FileUtil;
import com.powerhouse.interview.util.Validate;

@Controller
public class BusinessDelegate {

	@Autowired
	private ProfileService profileService;

	private FileUtil fileUtil = new FileUtil();
	private Validate validate = new Validate();
	private Converter converter = new Converter();
	
	public void handleProfile(MultipartFile file) throws IOException, BusinessFault {

		List<String> profiles = fileUtil.readLines(file);
		removeHeader(profiles);
		Map<String, Profile> inputProfileMap = converter.convertToProfileMap(profiles);
		validateProfiles(inputProfileMap);
		profileService.persistProfileMap(inputProfileMap);
	}

	public void removeHeader(List<String> profiles) {
		if (!profiles.isEmpty()) {
			String firstLine = profiles.get(0);
			if (firstLine.length() > 3 && Month.getValue(firstLine.substring(0, 3).toUpperCase()) == null) {				
				profiles.remove(0);
			}
		}
	}

	private void validateProfiles(Map<String, Profile> givenProfileMap) throws BusinessFault {
		for (Profile profile : givenProfileMap.values()) {
			if (!validate.fractions(profile)) {
				throw new BusinessFault("Given fractions are not valid for profile " + profile.getName());
			}
		}
	}
	
	public Profile getProfile(String key) {
		return profileService.getProfile(key);
	}

}
