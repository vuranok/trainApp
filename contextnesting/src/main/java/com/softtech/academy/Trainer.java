package com.softtech.academy;

public class Trainer {

	private String name;
	private String course;

	public Trainer(String name, String course) {
		super();
		this.name = name;
		this.course = course;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " is the trainer of course " + course;
	}
}

