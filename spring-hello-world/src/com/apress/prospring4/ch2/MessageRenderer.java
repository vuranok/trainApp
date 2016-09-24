package com.apress.prospring4.ch2;

public interface MessageRenderer {

	public void render();

	void setMessageProvider(MessageProvider provider);

	MessageProvider getMessageProvider();
}
