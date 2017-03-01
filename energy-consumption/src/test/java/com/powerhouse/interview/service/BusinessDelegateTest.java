package com.powerhouse.interview.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BusinessDelegateTest {

	@Test
	public void testRemoveHeaderForProfile() {
		BusinessDelegate businessDelegate = new BusinessDelegate();
		List<String> profiles = new ArrayList<String>();
		profiles.add("Month,Profile,Fraction");
		profiles.add("JAN,A,0.15");
		
		businessDelegate.removeHeaderForProfile(profiles);
		
		assertEquals(1, profiles.size());
	}
	
	@Test
	public void ifFirstRowIsAboutMonthThenItMustNotBeRemoved() {
		BusinessDelegate businessDelegate = new BusinessDelegate();
		List<String> profiles = new ArrayList<String>();
		profiles.add("JAN,A,0.15");
		
		businessDelegate.removeHeaderForProfile(profiles);
		
		assertEquals(1, profiles.size());
	}

}
