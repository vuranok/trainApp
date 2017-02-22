package com.powerhouse.interview;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ConsumptionControllerTest {

	ConsumptionController controller;
	
	@Before
	public void setup() {
		controller = new ConsumptionController();
	}
	
	@Test
	public void profileFileNameSuffixMustBeCsv() {
		String fileName = "profile.csv";
		
		assertTrue(controller.isFileNameSuffixCsv(fileName));
	}

	@Test
	public void suffixViolationWhenProfileFileNameSuffixIsOtherThanCsv() {
		String fileName = "profile.jpg";
		
		assertFalse(controller.isFileNameSuffixCsv(fileName));
	}
}
