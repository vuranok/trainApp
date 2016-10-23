package com.isbank;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * JAX–WS requires working with dedicated endpoint classes.
 * The endpoint instances are defined and managed as Spring beans themselves; 
 * they will be registered with the JAX–WS engine but their lifecycle will be up to the Spring application context. 
 * This means that Spring functionality like explicit dependency injection may be applied to the endpoint instances. 
 * Of course, annotation-driven injection through @Autowired will work as well.	
 */

@Service("accountServiceEndpoint")
@WebService(serviceName="AccountService")
public class AccountServiceEndpoint {

    @Autowired
    private AccountService biz;

    @WebMethod
    public void insertAccount(Account acc) {
        biz.insertAccount(acc);
    }

    @WebMethod
    public Account getAccount(Integer id) {
        return biz.getAccount(id);
    }

}