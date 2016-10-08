package com.isbank.spring.consinject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isbank.spring.message.MessageProvider;
import com.isbank.spring.message.MessageRenderer;

@Service("MessageRenderer")
public class StandardOutMessageRenderer implements MessageRenderer {

	private MessageProvider messageProvider;

	public void render() {
		
		if(messageProvider == null)
			throw new RuntimeException("You must specify MessageProvider of the class " + StandardOutMessageRenderer.class.getName());

		System.out.println(messageProvider.getMessage());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.isbank.spring.annotation.MessageRenderer#setMessageProvider(com.isbank.spring.annotation.MessageProvider)
	 */
	@Autowired
	public void setMessageProvider(MessageProvider messageProvider) {
		
		this.messageProvider = messageProvider;

	}

	public MessageProvider getMessageProvider() {
		return messageProvider;
	}

}
