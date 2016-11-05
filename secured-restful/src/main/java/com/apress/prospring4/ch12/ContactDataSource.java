package com.apress.prospring4.ch12;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class ContactDataSource implements ContactRepository {
	
	private Map<String, Contacts> dataSource = new TreeMap<String, Contacts>();

	public ContactDataSource() {
		Contact initialContact = new Contact();
		initialContact.setBirthDate(DateTime.now());
		initialContact.setFirstName("Chuck");
		initialContact.setId(0l);
		initialContact.setLastName("Noland");
		initialContact.setVersion(-1);
		
		this.save(initialContact);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Contact arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Contact> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Contact> findAll() {
		List<Contact> result = new ArrayList<Contact>();
		for(Contacts contacts : dataSource.values()) {
			result.addAll(contacts.getContacts());
		}
		return result;
	}

	@Override
	public Iterable<Contact> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Contact> S save(S arg0) {
		if(dataSource.containsKey(arg0.getFirstName())) {
			dataSource.get(arg0.getFirstName()).getContacts().add(arg0);
		}
		else {
			Contacts contacts = new Contacts(new ArrayList<Contact>());
			contacts.getContacts().add(arg0);
			dataSource.put(arg0.getFirstName(), contacts);
		}
		return arg0;
	}

	@Override
	public <S extends Contact> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

}
