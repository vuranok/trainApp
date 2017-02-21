package com.powerhouse.interview.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.powerhouse.interview.entity.BusinessFault;
import com.powerhouse.interview.entity.Profile;

public class ValidateTest {
	
	Validate validate;
	
	@Before
	public void setup() {
		validate = new Validate();
	}

	@Test
	public void profileFileNameSuffixMustBeCsv() {
		String fileName = "profile.csv";
		
		assertTrue(validate.fileNameSuffix(fileName));
	}

	@Test
	public void forAProfileTheSumOfAllFractionsShouldBe1() throws BusinessFault {
		Profile profile = ProfileBuilder.aProfile().withName("A").withSumOfFractionsOne(true).build();
		
		assertTrue(validate.fractions(profile));
	}
	
	@Test
	public void validationMustBeFalseWhenSumOfFractionsAreNot1() throws BusinessFault {
		Profile profile = ProfileBuilder.aProfile().withName("A").withSumOfFractionsOne(false).build();
		
		assertFalse(validate.fractions(profile));
	}
}
 