package com.apress.prospring4.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @Controller indicates that it’s a Spring MVC controller.
 * 
 * @RequestMapping;
 * 	In the class level : indicates that the controller will be mapped to all URLs under the main web context. In this sample, all URLs under http://localhost:8080/restful-spring/restful/contact will be handled by this controller.
 * 	In the method level : indicates the URL pattern and the corresponding HTTP method that it will be mapped to.
 * 
 * @ResponseBody : annotation is applied to all methods. This instructs that all the return values from the methods should be written to the HTTP response stream directly.
 * 
 * @PathVariable : This instructs Spring MVC to bind the path variable within the URL.
 * 
 * @RequestBody : This instructs Spring to automatically bind the content within the HTTP request body into the Contact domain object. 
 * 				  The conversion will be done by the declared instances of the HttpMessageConverter<Object> interface (under the package org.springframework.http.converter) for supporting formats.
 */

@Controller
@RequestMapping(value="/contact")
public class ContactController {
    final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/listdata", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseBody
    public Contacts listData() {
        return new Contacts(contactService.findAll());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Contact findContactById(@PathVariable Long id) {
        return contactService.findById(id);
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    @ResponseBody
    public Contact create(@RequestBody Contact contact) {
        logger.info("Creating contact: " + contact);
        contactService.save(contact);
        logger.info("Contact created successfully with info: " + contact);
        return contact;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Contact contact,
                       @PathVariable Long id) {
        logger.info("Updating contact: " + contact);
        contactService.save(contact);
        logger.info("Contact updated successfully with info: " + contact);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        logger.info("Deleting contact with id: " + id);
        Contact contact = contactService.findById(id);
        contactService.delete(contact);
        logger.info("Contact deleted successfully");
    }
}
