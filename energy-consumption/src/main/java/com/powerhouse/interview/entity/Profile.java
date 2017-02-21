package com.powerhouse.interview.entity;

import java.util.HashMap;
import java.util.Map;

public class Profile {

	private String name;
	private Map<Month, Double> fractionMap = new HashMap<Month, Double>();
	
	public Profile(String name) {
		this.name = name;
	}

	public void addFraction(String[] parts) throws BusinessFault {
		
		Month month = Month.getValue(parts[0].toUpperCase());
		if(month == null) {
			throw new BusinessFault("Given month value must be in {JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC}");
		}
		
		double fraction = Double.parseDouble(parts[2]);
		
		fractionMap.put(month, fraction);
	}

	public String getName() {
		return name;
	}

	public Map<Month, Double> getFractionMap() {
		return fractionMap;
	}
	
	
}
