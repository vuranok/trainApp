package com.powerhouse.interview.entity;

import java.util.Comparator;

public class MonthComparator implements Comparator<Month> {

	@Override
	public int compare(Month o1, Month o2) {
		return Integer.valueOf(o1.getIndex()).compareTo(o2.getIndex());
	}
}
