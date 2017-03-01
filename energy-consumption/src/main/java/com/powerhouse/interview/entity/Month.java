package com.powerhouse.interview.entity;

import org.apache.tomcat.util.net.AprEndpoint;

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

	public static Month getPreviousMonth(Month month) {
		if(month.equals(FEBRUARY)) {
			return Month.JANUARY;
		}
		else if(month.equals(MARCH)) {
			return Month.FEBRUARY;
		}
		else if(month.equals(APRIL)) {
			return Month.MARCH;
		}
		else if(month.equals(MAY)) {
			return Month.APRIL;
		}
		else if(month.equals(JUNE)) {
			return Month.MAY;
		}
		else if(month.equals(JULY)) {
			return Month.JUNE;
		}
		else if(month.equals(AUGUST)) {
			return Month.JULY;
		}
		else if(month.equals(SEPTEMBER)) {
			return Month.AUGUST;
		}
		else if(month.equals(OCTOBER)) {
			return Month.SEPTEMBER;
		}
		else if(month.equals(NOVEMBER)) {
			return Month.OCTOBER;
		}
		else if(month.equals(DECEMBER)) {
			return Month.NOVEMBER;
		}
		return null;
	}

}
