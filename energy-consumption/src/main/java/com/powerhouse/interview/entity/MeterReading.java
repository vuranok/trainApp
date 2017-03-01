package com.powerhouse.interview.entity;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.powerhouse.interview.BusinessFault;

public class MeterReading {

	private Integer meterID;
	private String profileName;
	private Map<Month, Integer> meterReadingMap = new TreeMap<Month, Integer>(new MonthComparator());
	
	
	public MeterReading() {
	}
	
	public MeterReading(Integer meterID, String profileName) {
		this.meterID = meterID;
		this.profileName = profileName;
	}

	public MeterReading(MeterReading meterReading) {
		this(meterReading.getMeterID(), meterReading.getProfileName());
		for(Entry<Month, Integer> entry : meterReading.getMeterReadingMap().entrySet()) {
			this.addReading(entry.getKey(), entry.getValue());
		}
	}

	public void setMeterID(Integer meterID) {
		this.meterID = meterID;
	}

	public void setMeterReadingMap(Map<Month, Integer> meterReadingMap) {
		this.meterReadingMap = meterReadingMap;
	}

	public void addReading(String[] parts) throws BusinessFault {
		
		Month month = Month.getValue(parts[2].toUpperCase());
		if(month == null) {
			throw new BusinessFault("Given month value must be in {JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC}");
		}
		
		int reading = Integer.parseInt(parts[3]);
		
		meterReadingMap.put(month, reading);
	}
	
	public void addReading(Month month, Integer reading) {
		
		meterReadingMap.put(month, reading);
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Map<Month, Integer> getMeterReadingMap() {
		return meterReadingMap;
	}

	public Integer getMeterID() {
		return meterID;
	}

	@Override
	public String toString() {
		return "MeterReading [meterID=" + meterID + ", profileName=" + profileName + ", meterReadingMap="
				+ meterReadingMap + "]";
	}
	
}
