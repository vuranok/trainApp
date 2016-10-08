package com.isbank.spring.consinject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("constructorConfusion")
public class ConstructorConfusion {

	private String someValue;

	public ConstructorConfusion(String someValue) {
		System.out.println("ConstructorConfusion(String) called");
		this.someValue = someValue;
	}

	/*
	 * The @Autowired annotation can be applied to only one of the constructor methods. If you apply the annotation
to more than one constructor method, Spring will complain during bootstrapping ApplicationContext.
	 */
	@Autowired
	public ConstructorConfusion(@Value("100") int someValue) {
		System.out.println("ConstructorConfusion(int) called with " + someValue);
		this.someValue = "Number: " + Integer.toString(someValue);
	}

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//		ctx.load("classpath:META-INF/spring/app-context.xml");
		ctx.load("classpath:META-INF/spring/app-context-annotation.xml");
		ctx.refresh();
		ConstructorConfusion cc = (ConstructorConfusion) ctx.getBean("constructorConfusion");
		System.out.println(cc);
	}

}
