package com.isbank.spring.consinject;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.isbank.spring.message.MessageProvider;

@Service("configurableMessageProvider")
public class ConfigurableMessageProvider implements MessageProvider {

	private String message;
	
	@Autowired
//	public ConfigurableMessageProvider(@Value("Configurable Message with the help of Value annotation and Autowired annotation") String message) {
	public ConfigurableMessageProvider(String message) {

		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
