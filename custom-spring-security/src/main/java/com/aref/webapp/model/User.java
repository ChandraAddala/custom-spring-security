package com.aref.webapp.model;

import java.util.Date;

/**
 * 
 * Similar to UserDetails object. But this is our custom user object which 
 * gets returned from the login service.
 * 
 * @author chandra
 *
 */
public final class User {

	private final String userName;
	
	private final String firstName;
	
	private final String lastName;
	
	private final Date dateOfBirth;
	
	private final String emailAddress;

	public User(String userName, 
				String firstName, 
				String lastName,
				Date dateOfBirth, 
				String emailAddress) {
		
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		
		//making defensive copy
		if (dateOfBirth != null) {
			this.dateOfBirth = new Date(dateOfBirth.getTime());			
		} else {
			this.dateOfBirth = null;
		}

		this.emailAddress = emailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	
	public Date getDateOfBirth() {
		return new Date(dateOfBirth.getTime());
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" userName=");
		sb.append(userName);

		sb.append(" firstName=");
		sb.append(firstName);

		sb.append(" lastName=");
		sb.append(lastName);
		
		sb.append(" dateOfBirth=");
		sb.append(dateOfBirth);
		
		sb.append(" emailAddress=");
		sb.append(emailAddress);

		return sb.toString();
	}
	
}
