package com.powerhouse.interview;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.powerhouse.interview.entity.BusinessFault;
import com.powerhouse.interview.entity.Month;
import com.powerhouse.interview.entity.Profile;
import com.powerhouse.interview.util.Converter;
import com.powerhouse.interview.util.FileUtil;
import com.powerhouse.interview.util.Validate;

public class ProfileService implements BusinessDelegate {

	private static final Map<String, Profile> PROFILE_MAP = java.util.Collections.synchronizedMap(new HashMap<String, Profile>());
	
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private Validate validate;
	@Autowired
	private Converter converter;

	@Override
	public void handle(MultipartFile file) throws IOException, BusinessFault {

		List<String> profiles = fileUtil.readLines(file);
		removeHeader(profiles);
		Map<String, Profile> inputProfileMap = converter.convertToProfileMap(profiles);
		validateProfiles(inputProfileMap);
		persistProfileMap(inputProfileMap);
	}

	public void removeHeader(List<String> profiles) {
		if (!profiles.isEmpty()) {
			String firstLine = profiles.get(0);
			if (firstLine.length() > 3 && Month.getValue(firstLine.substring(0, 3).toUpperCase()) == null)

				profiles.subList(1, profiles.size());
		}
	}

	private void validateProfiles(Map<String, Profile> givenProfileMap) throws BusinessFault {
		for (Profile profile : givenProfileMap.values()) {
			if (!validate.fractions(profile)) {
				throw new BusinessFault("Given fractions are not valid for profile " + profile.getName());
			}
		}
	}

	private void persistProfileMap(Map<String, Profile> givenProfileMap) {
		PROFILE_MAP.putAll(givenProfileMap);
	}

	public static Profile getProfile(String key) {
		return PROFILE_MAP.get(key);
	}

}