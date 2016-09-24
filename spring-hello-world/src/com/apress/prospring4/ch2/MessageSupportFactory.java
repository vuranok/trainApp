package com.apress.prospring4.ch2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MessageSupportFactory {
	private static MessageSupportFactory instance;

	private Properties properties;
	private MessageRenderer renderer;
	private MessageProvider provider;

	public MessageSupportFactory() {
		properties = new Properties();

		try {
			FileInputStream fileInputStream = new FileInputStream("resources\\imlementations.properties");

			properties.load(fileInputStream);

			String rendererClass = properties.getProperty("renderer");
			String providerClass = properties.getProperty("provider");

			renderer = (MessageRenderer) Class.forName(rendererClass).newInstance();
			provider = (MessageProvider) Class.forName(providerClass).newInstance();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static {
		instance = new MessageSupportFactory();
	}

	public static MessageSupportFactory getInstance() {
		return instance;
	}

	public MessageRenderer getRenderer() {
		return renderer;
	}

	public MessageProvider getProvider() {
		return provider;
	}

}
