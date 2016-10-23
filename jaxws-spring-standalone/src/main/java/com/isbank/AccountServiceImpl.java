package com.isbank;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

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
