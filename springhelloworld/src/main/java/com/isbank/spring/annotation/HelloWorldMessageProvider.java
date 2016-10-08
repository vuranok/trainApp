package com.isbank.spring.annotation;

import org.springframework.stereotype.Service;

import com.isbank.spring.message.MessageProvider;

@Service("messageProvider")
public class HelloWorldMessageProvider implements MessageProvider {

	public String getMessage() {
		
		return HelloWorldMessageProvider.class.getName();
	}

}
