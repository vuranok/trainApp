package com.powerhouse.interview.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Profile;

@Service
@Repository
@Transactional
public class BusinessService {

	private static final Map<String, Profile> PROFILE_MAP = java.util.Collections.synchronizedMap(new HashMap<String, Profile>());
	private static final Map<Integer, MeterReading> METER_MAP = java.util.Collections.synchronizedMap(new HashMap<Integer, MeterReading>());

	private ProfileRepository profileRepository;
	private MeterRepository meterRepository;

	@Autowired
	public void setProfileRepository(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Autowired
	public void setMeterRepository(MeterRepository meterRepository) {
		this.meterRepository = meterRepository;
	}
	
	public void persistProfileMap(Map<String, Profile> givenProfileMap) {
		for (Profile profile : givenProfileMap.values()) {
			profileRepository.save(profile);
		}
		for (Profile profile : givenProfileMap.values()) {
			PROFILE_MAP.put(profile.getName(), new Profile(profile));
		}
	}

	public Profile getProfile(String key) {
		if (PROFILE_MAP.containsKey(key)) {
			return new Profile(PROFILE_MAP.get(key));
		} else {
			List<Profile> profilesInDb = profileRepository.findOne(key);
			if (!profilesInDb.isEmpty()) {
				PROFILE_MAP.put(key, profilesInDb.get(0));
				return new Profile(profilesInDb.get(0));
			}
			return null;
		}
	}

	public void persistMeterReadingMap(Map<Integer, MeterReading> givenMeterReadingMap) {
		for (MeterReading meterReading : givenMeterReadingMap.values()) {
			meterRepository.save(meterReading);
		}
		for (MeterReading meterReading : givenMeterReadingMap.values()) {
			METER_MAP.put(meterReading.getMeterID(), new MeterReading(meterReading));
		}
	}
	
	public MeterReading getMeterReading(Integer id) {
		if (METER_MAP.containsKey(id)) {
			return new MeterReading(METER_MAP.get(id));
		} else {
			List<MeterReading> metersInDb = meterRepository.findOne(id);
			if (!metersInDb.isEmpty()) {
				METER_MAP.put(id, metersInDb.get(0));
				return new MeterReading(metersInDb.get(0));
			}
			return null;
		}
	}

	public List<Profile> fetchProfiles() {
		return profileRepository.fetchProfiles();
	}

	public List<MeterReading> fetchMeterReadings() {
		return meterRepository.fetchMeterReadings();
		}

}