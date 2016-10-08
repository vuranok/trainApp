package com.isbank.spring.basics;

import com.isbank.spring.message.MessageProvider;
import com.isbank.spring.message.MessageRenderer;

public class StandardOutMessageRenderer implements MessageRenderer {

	private MessageProvider messageProvider;

	public void render() {
		
		if(messageProvider == null)
			throw new RuntimeException("You must specify MessageProvider of the class " + StandardOutMessageRenderer.class.getName());

		System.out.println(messageProvider.getMessage());
	}

	public void setMessageProvider(MessageProvider messageProvider) {
		
		this.messageProvider = messageProvider;

	}

	public MessageProvider getMessageProvider() {
		return messageProvider;
	}

}
