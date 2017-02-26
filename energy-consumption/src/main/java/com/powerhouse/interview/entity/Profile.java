package com.powerhouse.interview.entity;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Profile {

	private String name;
	private Map<Month, Double> fractionMap = new TreeMap<Month, Double>(new MonthComparator());
	
	public Profile(String name) {
		this.name = name;
	}
	
	public Profile(Profile aProfile) {
		this(aProfile.getName());
		for(Entry<Month, Double> entry : aProfile.getFractionMap().entrySet()) {
			this.addFraction(entry.getKey(), entry.getValue());
		}
	}

	public String getName() {
		return name;
	}

	public Map<Month, Double> getFractionMap() {
		return fractionMap;
	}

	public void addFraction(String[] parts) throws BusinessFault {
		
		Month month = Month.getValue(parts[0].toUpperCase());
		if(month == null) {
			throw new BusinessFault("Given month value must be in {JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC}");
		}
		
		double fraction = Double.parseDouble(parts[2]);
		
		fractionMap.put(month, fraction);
	}
	
	public void addFraction(Month month, double fraction) {
		
		fractionMap.put(month, fraction);
	}
	
	@Override
	public String toString() {
		return "Profile [name=" + name + ", fractionMap=" + fractionMap + "]";
	}
	
}
