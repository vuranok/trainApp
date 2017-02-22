package com.powerhouse.interview;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ProfileControllerTest {

	ProfileController controller;
	
	@Before
	public void setup() {
		controller = new ProfileController();
	}
	
	@Test
	public void profileFileNameSuffixMustBeCsv() {
		String fileName = "profile.csv";
		
		assertTrue(controller.fileNameSuffix(fileName));
	}

	@Test
	public void suffixViolationWhenProfileFileNameSuffixIsOtherThanCsv() {
		String fileName = "profile.jpg";
		
		assertFalse(controller.fileNameSuffix(fileName));
	}
}
