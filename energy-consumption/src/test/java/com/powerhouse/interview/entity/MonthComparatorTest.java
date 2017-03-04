package com.powerhouse.interview.entity;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class MonthComparatorTest {

	@Test
	public void testMonthOrder() {
		Map<Month, Integer> map = new TreeMap<Month, Integer>(new MonthComparator());
		map.put(Month.MAY, 1);
		map.put(Month.DECEMBER, 1);
		map.put(Month.OCTOBER, 1);
		map.put(Month.NOVEMBER, 1);
		map.put(Month.AUGUST, 1);
		map.put(Month.JULY, 1);
		map.put(Month.JUNE, 1);
		map.put(Month.APRIL, 1);
		map.put(Month.SEPTEMBER, 1);
		map.put(Month.MARCH, 1);
		map.put(Month.FEBRUARY, 1);
		map.put(Month.JANUARY, 1);

		Month[] arr = new Month[12];
		map.keySet().toArray(arr);

		assertEquals(Month.JANUARY, arr[0]);
		assertEquals(Month.FEBRUARY, arr[1]);
		assertEquals(Month.MARCH, arr[2]);
		assertEquals(Month.APRIL, arr[3]);
		assertEquals(Month.MAY, arr[4]);
		assertEquals(Month.JUNE, arr[5]);
		assertEquals(Month.JULY, arr[6]);
		assertEquals(Month.AUGUST, arr[7]);
		assertEquals(Month.SEPTEMBER, arr[8]);
		assertEquals(Month.OCTOBER, arr[9]);
		assertEquals(Month.NOVEMBER, arr[10]);
		assertEquals(Month.DECEMBER, arr[11]);

	}

}
