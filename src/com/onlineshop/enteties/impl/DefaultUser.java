package com.onlineshop.enteties.impl;

import com.onlineshop.enteties.User;

public class DefaultUser implements User {

	private static int userCounter = 0;

	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private int id;

	{
		id = ++userCounter;
	}

	public DefaultUser() {
	}

	public DefaultUser(String firstName, String lastName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}
	
	public DefaultUser(int id, String firstName, String lastName, String password, String email) {
		this.id = id;
		userCounter--;  //to maintain sequential id
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String toString() {
		return "First Name: " + this.getFirstName() + "\t\t" + "Last Name: " + this.getLastName() + "\t\t" + "Email: "
				+ this.getEmail();
	}

	@Override
	public void setPassword(String password) {
		if (password == null) {
			return;
		}
		this.password = password;
	}

	@Override
	public void setEmail(String newEmail) {
		if (newEmail == null) {
			return;
		}
		this.email = newEmail;
	}

	@Override
	public int getId() {
		return this.id;
	}

	void clearState() {
		userCounter = 0;
	}
	
	public static void setCounter(int updatedCount) {
		userCounter = updatedCount;
	}	
}
