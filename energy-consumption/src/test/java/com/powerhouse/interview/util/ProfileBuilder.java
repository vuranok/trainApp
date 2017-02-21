package com.powerhouse.interview.util;

import com.powerhouse.interview.entity.BusinessFault;
import com.powerhouse.interview.entity.Profile;

public class ProfileBuilder {

	private String name;
	private boolean sumOfFractionsOne;

	public static ProfileBuilder aProfile() {
		return new ProfileBuilder();
	}
	
	public ProfileBuilder withName(String name) {
		this.name = name; 
		return this;
	}
	
	public ProfileBuilder withSumOfFractionsOne(boolean sumOfFractionsOne) {
		this.sumOfFractionsOne = sumOfFractionsOne; 
		return this;
	}
	
	public Profile build() throws BusinessFault {
		Profile profile = new Profile(name);

		if(sumOfFractionsOne) {
			profile.addFraction(new String[] {"JAN","A","0.15"});
			profile.addFraction(new String[] {"FEB","A","0.17"});
			profile.addFraction(new String[] {"MAR","A","0.13"});
			profile.addFraction(new String[] {"APR","A","0.08"});
			profile.addFraction(new String[] {"MAY","A","0.08"});
			profile.addFraction(new String[] {"JUN","A","0"});
			profile.addFraction(new String[] {"JUL","A","0"});
			profile.addFraction(new String[] {"AUG","A","0.01"});
			profile.addFraction(new String[] {"SEP","A","0.04"});
			profile.addFraction(new String[] {"OCT","A","0.09"});
			profile.addFraction(new String[] {"NOV","A","0.1"});
			profile.addFraction(new String[] {"DEC","A","0.15"});
		}
		else {
			profile.addFraction(new String[] {"JAN","A","0.1"});
			profile.addFraction(new String[] {"FEB","A","0.1"});
			profile.addFraction(new String[] {"MAR","A","0.1"});
			profile.addFraction(new String[] {"APR","A","0.1"});
			profile.addFraction(new String[] {"MAY","A","0.1"});
			profile.addFraction(new String[] {"JUN","A","0.1"});
			profile.addFraction(new String[] {"JUL","A","0.1"});
			profile.addFraction(new String[] {"AUG","A","0.1"});
			profile.addFraction(new String[] {"SEP","A","0.1"});
			profile.addFraction(new String[] {"OCT","A","0.1"});
			profile.addFraction(new String[] {"NOV","A","0.1"});
			profile.addFraction(new String[] {"DEC","A","0.1"});
		}

		return profile;
	}
}
