package com.isbank;

public class TestDataManager {

    public Customer getCustomer(int customerID) {
    	
    	Customer customer = new Customer();
		customer.setName("Onur");
		customer.setNumber(customerID);
		
		return customer;
    }
    
}
