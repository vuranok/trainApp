package com.softtech.academy.beaninheritance;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Runner {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/app-context.xml");
		ctx.refresh();
		
		App parent = (App) ctx.getBean("parent");
		App child = (App) ctx.getBean("child");
		
		System.out.println("Parent:\n" + parent);
		System.out.println("Child:\n" + child);
	}

}
