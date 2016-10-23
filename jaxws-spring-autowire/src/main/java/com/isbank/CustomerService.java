package com.isbank;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/*
 * A class that’s annotated with @WebService is considered a web service endpoint.
 * @WebMethod annotated methods are the operations. 
 */

@WebService(targetNamespace="http://com/isbank/")
@SOAPBinding(style=Style.DOCUMENT)
public interface CustomerService {

	@WebMethod 
	@WebResult(name="customer")
    Customer getCustomer(int customerID);

}
