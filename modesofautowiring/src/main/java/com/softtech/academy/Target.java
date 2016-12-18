package com.softtech.academy;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Target {

	private Foo foo;
	private Foo foo2;
	private Bar bar;
	
	public Target() {
		
	}
	
	public Target(Foo foo) {
		System.out.println("Target(Foo) called");
	}
	
	public Target(Foo foo, Bar bar) {
		System.out.println("Target(Foo, Bar) called");
	}
	
	public void setFoo(Foo foo) {
		this.foo = foo;
		System.out.println("Property foo set");
	}
	
	public void setFoo2(Foo foo) {
		this.foo2 = foo;
		System.out.println("Property foo2 set");
	}
	
	public void setBar(Bar bar) {
		this.bar = bar;
		System.out.println("Property bar set");
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("META-INF/application-context.xml");
		ctx.refresh();
		
		Target t = null;
		System.out.println("Using by name:\n");
		t = (Target) ctx.getBean("targetByName");
		
		System.out.println("Using by type:\n");
		t = (Target) ctx.getBean("targetByType");
		
		System.out.println("Using constructor:\n");
		t = (Target) ctx.getBean("targetByConstructor");
	}
}
