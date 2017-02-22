package com.powerhouse.interview.entity;

public enum Month {

	JANUARY("JAN", 1), FEBRUARY("FEB", 2), MARCH("MAR", 3), APRIL("APR", 4), MAY("MAY", 5), JUNE("JUN", 6), JULY("JUL", 7), AUGUST(
			"AUG", 8), SEPTEMBER("SEP", 9), OCTOBER("OCT", 10), NOVEMBER("NOV", 11), DECEMBER("DEC", 12);

	private final String value;
	private int index;

	Month(String value, int index) {
		this.value = value;
		this.index = index;
	}

	public static Month getValue(String inputMonth) {
		for (Month month : Month.values()) {
			if (month.value.equals(inputMonth)) {
				return month;
			}
		}
		return null;
	}

	public int getIndex() {
		return index;
	}

}
