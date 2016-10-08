package com.isbank.spring.annotation;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.isbank.spring.message.MessageRenderer;

public class SpringAnnotationRunner {

	public static void main(String[] args) {

		/*
		 * 
		 */
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();

		ctx.load("classpath:META-INF/spring/app-context-annotation.xml");
		ctx.refresh();
		
		/*
		MessageProvider messageProvider = ctx.getBean("messageProvider", MessageProvider.class);
		System.out.println(messageProvider.getMessage());
		*/
		
		MessageRenderer messageRenderer = ctx.getBean("MessageRenderer", MessageRenderer.class);
		messageRenderer.render();
		
	}

}
