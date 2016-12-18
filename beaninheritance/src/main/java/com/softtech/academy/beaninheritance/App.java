package com.softtech.academy.beaninheritance;

/**
 * Hello world!
 *
 */
public class App 
{
	private String name;
	private int version;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return "Name : " + this.name + " Version: " + this.version;
	}
}
