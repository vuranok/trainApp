package com.isbank.spring.message;

public interface MessageRenderer {

	public void render();
	
	public void setMessageProvider(MessageProvider messageProvider);
	
	public MessageProvider getMessageProvider();
	
}
