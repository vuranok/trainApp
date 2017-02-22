package com.powerhouse.interview.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerhouse.interview.entity.Profile;

@Service
@Repository
@Transactional
public class ProfileService {

	private static final Map<String, Profile> PROFILE_MAP = java.util.Collections.synchronizedMap(new HashMap<String, Profile>());
	
	private ProfileRepository profileRepository;
	
	public void persistProfileMap(Map<String, Profile> givenProfileMap) {
		for(Profile profile : givenProfileMap.values()) {
			profileRepository.save(profile);
		}
		PROFILE_MAP.putAll(givenProfileMap);
	}

	public Profile getProfile(String key) {
		return PROFILE_MAP.get(key);
	}

    @Autowired
    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    
}