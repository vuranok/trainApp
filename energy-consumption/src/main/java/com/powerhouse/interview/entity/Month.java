package com.powerhouse.interview.entity;

public enum Month {

	JANUARY("JAN"),
	FEBRUARY("FEB"),
	MARCH("MAR"),
	APRIL("APR"),
	MAY("MAY"),
	JUNE("JUN"),
	JULY("JUL"),
	AUGUST("AUG"),
	SEPTEMBER("SEP"),
	OCTOBER("OCT"),
	NOVEMBER("NOV"),
	DECEMBER ("DEC");

    private final String value;
	
	Month(String value) {
        this.value = value;
    }
	
	public static Month getValue(String inputMonth) {
		for (Month month : Month.values()) {
	         if (month.value.equals(inputMonth)) {
	             return month;
	         }
	     }
		return null;
	}
}
