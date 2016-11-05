package com.apress.prospring4.ch12;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.impl.client.BasicCredentialsProvider;

public class CustomCredentialsProvider extends BasicCredentialsProvider {

	/*
	 * To support the injection of credentials, we implement a simple CustomCredentialsProvider class 
	 */
	
	public void setCredentials(Credentials credentials) {
		this.setCredentials(AuthScope.ANY, credentials);
		}
	
}
