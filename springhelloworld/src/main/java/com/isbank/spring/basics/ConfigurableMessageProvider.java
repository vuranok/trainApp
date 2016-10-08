package com.isbank.spring.basics;

import com.isbank.spring.basics.MessageProvider;

public class ConfigurableMessageProvider implements MessageProvider {

	private String message;
	
	
	public ConfigurableMessageProvider(String message) {

		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
