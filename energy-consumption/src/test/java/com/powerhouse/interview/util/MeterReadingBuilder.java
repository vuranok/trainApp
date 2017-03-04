package com.powerhouse.interview.util;

import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Month;

public class MeterReadingBuilder {

	private Integer id;
	private String profileName;
	private Double januaryReading;
	private Double februaryReading;
	private Double marchReading;
	private Double aprilReading;
	private Double mayReading;
	private Double juneReading;
	private Double julyReading;
	private Double augustReading;
	private Double septemberReading;
	private Double octoberReading;
	private Double novemberReading;
	private Double decemberReading;

	public static MeterReadingBuilder aMeterReading() {
		return new MeterReadingBuilder();
	}
	
	public MeterReadingBuilder withMeterId(Integer id) {
		this.id = id; 
		return this;
	}

	public MeterReadingBuilder withJanuaryReading(Double januaryReading) {
		this.januaryReading = januaryReading; 
		return this;
	}
	
	public MeterReadingBuilder withFebruaryReading(Double februaryReading) {
		this.februaryReading = februaryReading; 
		return this;
	}
	
	public MeterReadingBuilder withMarchReading(Double marchReading) {
		this.marchReading = marchReading; 
		return this;
	}
	
	public MeterReadingBuilder withAprilReading(Double aprilReading) {
		this.aprilReading = aprilReading; 
		return this;
	}
	
	public MeterReadingBuilder withMayReading(Double mayReading) {
		this.mayReading = mayReading; 
		return this;
	}
	
	public MeterReadingBuilder withJuneReading(Double juneReading) {
		this.juneReading = juneReading; 
		return this;
	}
	
	public MeterReadingBuilder withJulyReading(Double julyReading) {
		this.julyReading = julyReading; 
		return this;
	}
	
	public MeterReadingBuilder withAugustReading(Double augustReading) {
		this.augustReading = augustReading; 
		return this;
	}
	
	public MeterReadingBuilder withSeptemberReading(Double septemberReading) {
		this.septemberReading = septemberReading; 
		return this;
	}
	
	public MeterReadingBuilder withOctoberReading(Double octoberReading) {
		this.octoberReading = octoberReading; 
		return this;
	}
	
	public MeterReadingBuilder withNovemberReading(Double novemberReading) {
		this.novemberReading = novemberReading; 
		return this;
	}
	
	public MeterReadingBuilder withDecemberReading(Double decemberReading) {
		this.decemberReading = decemberReading; 
		return this;
	}
	
	public MeterReadingBuilder withProfileName(String profileName) {
		this.profileName = profileName; 
		return this;
	}
	
	public MeterReading build() {
		MeterReading profile = new MeterReading(id, profileName);
		
		profile.addReading(Month.JANUARY, januaryReading);
		profile.addReading(Month.FEBRUARY, februaryReading);
		profile.addReading(Month.MARCH, marchReading);
		profile.addReading(Month.APRIL, aprilReading);
		profile.addReading(Month.MAY, mayReading);
		profile.addReading(Month.JUNE, juneReading);
		profile.addReading(Month.JULY, julyReading);
		profile.addReading(Month.AUGUST, augustReading);
		profile.addReading(Month.SEPTEMBER, septemberReading);
		profile.addReading(Month.OCTOBER, octoberReading);
		profile.addReading(Month.NOVEMBER, novemberReading);
		profile.addReading(Month.DECEMBER, decemberReading);
		
		return profile;
	}
}
