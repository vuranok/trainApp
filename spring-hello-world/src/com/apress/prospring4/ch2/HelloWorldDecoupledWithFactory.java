package com.apress.prospring4.ch2;

public class HelloWorldDecoupledWithFactory {

	public static void main(String[] args) {
		MessageProvider provider = MessageSupportFactory.getInstance().getProvider();
		MessageRenderer renderer = MessageSupportFactory.getInstance().getRenderer();

		renderer.setMessageProvider(provider);
		renderer.render();
	}
}
