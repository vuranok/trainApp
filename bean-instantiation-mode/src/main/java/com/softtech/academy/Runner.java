package com.softtech.academy;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Runner {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/app-context.xml");
		ctx.refresh();

		System.out.println("\ndefault scope singleton\n");
		String granny1 = (String) ctx.getBean("granny-elma");
		String granny2 = (String) ctx.getBean("granny-elma");

		System.out.println("Identity Equal?: " + (granny1 == granny2));
		System.out.println("Value Equal:? " + granny1.equals(granny2));
		System.out.println(granny1);
		System.out.println(granny2);

		System.out.println("\nscope prototype\n");
		String golden1 = (String) ctx.getBean("golden-elma");
		String golden2 = (String) ctx.getBean("golden-elma");

		System.out.println("Identity Equal?: " + (golden1 == golden2));
		System.out.println("Value Equal:? " + golden1.equals(golden2));
		System.out.println(golden1);
		System.out.println(golden2);
		
	}

}
