package com.isbank.spring.basics;

public interface MessageRenderer {

	public void render();
	
	public void setMessageProvider(MessageProvider messageProvider);
	
	public MessageProvider getMessageProvider();
	
}
