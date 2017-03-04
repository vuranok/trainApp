package com.powerhouse.interview.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MonthTest {

	@Test
	public void testPreviousMonth() {

		assertEquals(null, Month.getPreviousMonth(Month.JANUARY));
		assertEquals(Month.JANUARY, Month.getPreviousMonth(Month.FEBRUARY));
		assertEquals(Month.FEBRUARY, Month.getPreviousMonth(Month.MARCH));
		assertEquals(Month.MARCH, Month.getPreviousMonth(Month.APRIL));
		assertEquals(Month.APRIL, Month.getPreviousMonth(Month.MAY));
		assertEquals(Month.MAY, Month.getPreviousMonth(Month.JUNE));
		assertEquals(Month.JUNE, Month.getPreviousMonth(Month.JULY));
		assertEquals(Month.JULY, Month.getPreviousMonth(Month.AUGUST));
		assertEquals(Month.AUGUST, Month.getPreviousMonth(Month.SEPTEMBER));
		assertEquals(Month.SEPTEMBER, Month.getPreviousMonth(Month.OCTOBER));
		assertEquals(Month.OCTOBER, Month.getPreviousMonth(Month.NOVEMBER));
		assertEquals(Month.NOVEMBER, Month.getPreviousMonth(Month.DECEMBER));

	}

}
