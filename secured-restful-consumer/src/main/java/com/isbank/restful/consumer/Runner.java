package com.isbank.restful.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.apress.prospring4.ch12.Contact;
import com.apress.prospring4.ch12.Contacts;

public class Runner {
	private static final String URL_GET_ALL_CONTACTS = "http://localhost:8081/secured-restful/restful/contact/listdata";
	private static final String URL_GET_CONTACT_BY_ID = "http://localhost:8080/secured-restful/restful/contact/{id}";
	private static final String URL_CREATE_CONTACT = "http://localhost:8080/secured-restful/restful/contact/";
	private static final String URL_UPDATE_CONTACT = "http://localhost:8080/secured-restful/restful/contact/{id}";
	private static final String URL_DELETE_CONTACT = "http://localhost:8080/secured-restful/restful/contact/{id}";

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");

		RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);

		System.out.println("Testing retrieve all contacts:");
		Contacts contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
		listContacts(contacts);

	}

	private static void listContacts(Contacts contacts) {
		for (Contact contact : contacts.getContacts()) {
			System.out.println(contact);
		}
		System.out.println("");
	}

}
