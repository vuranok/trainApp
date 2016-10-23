package com.isbank.client;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.isbank.Account;
import com.isbank.AccountServiceEndpoint;

public class AccountClientImpl {

	public AccountServiceEndpoint service;

	public AccountClientImpl() {
		// TODO Auto-generated constructor stub
	}

	public void setService(AccountServiceEndpoint arg0) {
		
		this.service = arg0;
	}

	public void insertAccount(Account account) {
		service.insertAccount(account);
	}
	
	public Account getAccount(Integer arg0) {
		return service.getAccount(arg0);
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();

		ctx.load("classpath:spring/application-context.xml");
		ctx.refresh();
		
		AccountClientImpl accountClientImpl = (AccountClientImpl) ctx.getBean("client");
		Account account = new Account();
		account.setId(5);
		account.setName("asdfa");
		accountClientImpl.insertAccount(account);
		
		System.out.println(accountClientImpl.getAccount(5).getName());
	}
}
