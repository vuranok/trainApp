package com.apress.prospring4.ch2;

public class HelloWorldDecoupled {

	public static void main(String[] args) {

		MessageProvider provider = new HelloWorldMessageProvider();
		MessageRenderer renderer = new StandardOutMessageRenderer();

		renderer.setMessageProvider(provider);
		renderer.render();
	}

}
