package com.powerhouse.interview;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.powerhouse.interview.entity.BusinessFault;
import com.powerhouse.interview.entity.Profile;

public interface BusinessDelegate {

	void handleProfile(MultipartFile file) throws IOException, BusinessFault;

	Profile getProfile(String key);

}
