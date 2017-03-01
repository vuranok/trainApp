package com.powerhouse.interview.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.powerhouse.interview.BusinessFault;
import com.powerhouse.interview.entity.Month;
import com.powerhouse.interview.entity.Profile;

public class ConverterTest {

	Converter converter;
	
	@Before
	public void setup() {
		converter = new Converter();
	}
	
	@Test(expected=BusinessFault.class)
	public void commaMustBeUsedAsALineSeperator() throws BusinessFault {
		List<String> inputList = new ArrayList<String>();
		
		inputList.add("JAN,A;0.15");
		converter.convertToProfilesFromCommaSeperatedStrings(inputList);
	}

	@Test
	public void givenMonthValueMustBeIn_JAN_FEB_MAR_APR_MAY_JUN_JUL_AUG_SEP_OCT_NOV_DEC() throws BusinessFault {
		List<String> inputList = new ArrayList<String>();
		
		inputList.add("JAN,A,0.15");
		inputList.add("FEB,A,0.17");
		inputList.add("MAR,A,0.13");
		inputList.add("APR,A,0.08");
		inputList.add("MAY,A,0.08");
		inputList.add("JUN,A,0");
		inputList.add("JUL,A,0");
		inputList.add("AUG,A,0.01");
		inputList.add("SEP,A,0.04");
		inputList.add("OCT,A,0.09");
		inputList.add("NOV,A,0.1");
		inputList.add("DEC,A,0.15");
		
		Collection<Profile> profiles = converter.convertToProfilesFromCommaSeperatedStrings(inputList);
		
		assertEquals(1, profiles.size());
		assertEquals(12, profiles.iterator().next().getFractionMap().size());
	}
	
	@Test
	public void givenTwoSeperateProfilesMustBeInTheConvertedList() throws BusinessFault {
		List<String> inputList = new ArrayList<String>();
		
		inputList.add("JAN,A,0.2");
		inputList.add("JAN,B,0.18");
		inputList.add("FEB,A,0.1");
		inputList.add("FEB,B,0.21");
		
		Collection<Profile> profiles = converter.convertToProfilesFromCommaSeperatedStrings(inputList);
		
		assertEquals(2, profiles.size());
		for(Profile profile : profiles) {
			if(profile.getName().equals("A")) {
				assertEquals(2, profile.getFractionMap().size());
				assertEquals(0.2d, profile.getFractionMap().get(Month.JANUARY).doubleValue(), 0);
				assertEquals(0.1d, profile.getFractionMap().get(Month.FEBRUARY).doubleValue(), 0);				
			}
			else {				
				assertEquals(2, profile.getFractionMap().size());
				assertEquals(0.18d, profile.getFractionMap().get(Month.JANUARY).doubleValue(), 0);
				assertEquals(0.21d, profile.getFractionMap().get(Month.FEBRUARY).doubleValue(), 0);
			}
		}
	}
}
