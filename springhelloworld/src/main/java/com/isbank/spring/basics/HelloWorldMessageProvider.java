package com.isbank.spring.basics;

import com.isbank.spring.message.MessageProvider;

public class HelloWorldMessageProvider implements MessageProvider {

	public String getMessage() {
		
		return HelloWorldMessageProvider.class.getName();
	}

}
