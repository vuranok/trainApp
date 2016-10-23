package com.isbank;

//import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/*
 * If the endpoint’s lifecycle is managed by the JAX-WS runtime, not by Spring, 
 * it would seem to be impossible to wire Spring-managed beans into a JAX-WS–managed endpoint instance.
 * 
 * By extending SpringBeanAutowiringSupport, you can annotate an endpoint’s properties with @Autowired, and its dependencies will be met.
 */

@WebService(endpointInterface="com.isbank.CustomerService")
public class CustomerServiceImpl extends SpringBeanAutowiringSupport implements CustomerService {

    @Autowired
    private TestDataManager testDataManager;
    
//	@PostConstruct
//    public void postConstruct() {
//        System.out.println("postconstruct has run.");
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//    }
	
	@Override
	public Customer getCustomer(int customerID) {
		return testDataManager.getCustomer(customerID);
	}

}
