package com.powerhouse.interview;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.powerhouse.interview.entity.Profile;
import com.powerhouse.interview.service.BusinessDelegate;

@RestController
@RequestMapping(value="/energy")
public class ConsumptionRestController {

	@Autowired
	BusinessDelegate businessDelegate;
	
	@RequestMapping(value = "/profiles", method = RequestMethod.POST)
	public String createProfiles(@RequestBody ArrayList<Profile> profiles) {
		try {
			businessDelegate.createProfiles(profiles);
		} catch (BusinessFault e) {
			return e.getMessage();
		}
		return "All profiles are created.";
	}
	
	@RequestMapping(value = "/profiles", method = RequestMethod.GET)
	public @ResponseBody List<Profile> getProfiles() {
		return businessDelegate.fetchProfiles();
	}
	
}
