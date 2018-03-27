package com.radostin.model;

import java.util.Date;

public class User {
	protected int id;
	protected String firstName;
	protected String lastName;
	protected Date birthdate;
	protected String phoneNumber;
	protected String email;

	/**
	 * Empty constructor.
	 */
	public User() {

	}

	/**
	 * User constructor.
	 * 
	 * @param id
	 *            - identity number of the user
	 * @param firstName
	 *            - first name of the user
	 * @param lastName
	 *            - last name of the user
	 * @param birthdate
	 *            - birth date of the user
	 * @param phoneNumber
	 *            - phone number of the user
	 * @param email
	 *            - email of the user
	 */
	public User(int id, String firstName, String lastName, Date birthdate, String phoneNumber, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public User(String firstName, String lastName, Date birthdate, String phoneNumber, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
