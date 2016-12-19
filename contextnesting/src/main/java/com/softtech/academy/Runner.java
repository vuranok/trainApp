package com.softtech.academy;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Runner {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext parent = new GenericXmlApplicationContext();
		
		parent.load("META-INF/spring/parent.xml");
		parent.refresh();
		
		GenericXmlApplicationContext child = new GenericXmlApplicationContext();
		child.load("META-INF/spring/app-context.xml");
		child.setParent(parent);
		child.refresh();
		
		System.out.println(child.getBean("hande"));
		System.out.println(child.getBean("serkan"));
		System.out.println(child.getBean("onur"));

	}

}
