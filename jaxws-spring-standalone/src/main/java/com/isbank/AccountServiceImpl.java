package com.isbank;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

/*
 * @Service : annotation to specify that the bean provides services that other beans may require, 
 * passing in the bean name as the parameter
 * 
 * @Component has the same effect as @Service Both annotations are instructing Spring that the annotated class is a
	candidate for autodetection using annotation-based configuration and classpath scanning. However, since the
	InjectSimpleConfig class is storing the application configuration, rather than providing a business service, using
	@Component makes more sense. Practically, @Service is a specialization of @Component, which indicates that the
	annotated class is providing a business service to other layers within the application.
	
	@Component("injectSimpleConfig")
		public class InjectSimpleConfig {
		private String name = "John Smith";
		private int age = 35;
		private float height = 1.78f;
		private boolean programmer = true;
		private Long ageInSeconds = 1103760000L;
	}

 */
@Service
public class AccountServiceImpl implements AccountService {

	private Map<Integer, Account> accounts = new TreeMap<Integer, Account>();
	
	@Override
	public void insertAccount(Account account) {
		
		if(account != null && account.getId() != null) {
			accounts.put(account.getId(), account);
		}

	}

	@Override
	public Account getAccount(Integer key) {
		
		return accounts.get(key);
	}

}
