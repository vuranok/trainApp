package com.isbank.spring.basics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.isbank.spring.message.MessageRenderer;

public class HelloWorldSpringDI {

	public static void main(String[] args) {

		/*
		 * The application configuration information is loaded from the file META-INF/spring/app-context.xml in the project’s classpath
		 */
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		
		/*
		 * getBean() method reads the application configuration (in this case, an XML file), initializes Spring’s ApplicationContext environment, and then returns the configured bean instance
		 */
		MessageRenderer messageRenderer = applicationContext.getBean("renderer", MessageRenderer.class);
		
		messageRenderer.render();
		
	}

}
