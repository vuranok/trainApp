package com.powerhouse.interview;

public class BusinessFault extends Exception {

	private static final long serialVersionUID = 4700271435008894397L;

	public BusinessFault(String message) {
		super(message);
	}

}
