package com.isbank;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customer",namespace="http://com/isbank/")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private int number;
	
	@XmlElement(nillable=false,namespace="http://com/isbank/")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(nillable=false,namespace="http://com/isbank/")
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
