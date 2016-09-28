package com.isbank.spring.basics;

public class HelloWorldMessageProvider implements MessageProvider {

	public String getMessage() {
		
		return HelloWorldMessageProvider.class.getName();
	}

}
