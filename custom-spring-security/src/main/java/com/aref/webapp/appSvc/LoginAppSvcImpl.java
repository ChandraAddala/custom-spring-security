package com.aref.webapp.appSvc;

import java.util.Calendar;
import java.util.Date;

import com.aref.webapp.exception.AppSvcException;
import com.aref.webapp.model.User;

public class LoginAppSvcImpl implements ILoginAppSvc {

	/**
	 * 
	 * This method authenticates the user using userName, password
	 * and company and for an user with valid credentials User object is returned.
	 * 
	 * @param userName
	 * @param password
	 * @param company
	 * @return User
	 */
	public User login(String userName, String password, String company)
			throws AppSvcException {

		Calendar cal = Calendar.getInstance();
		cal.set(1966, 9, 2);
		
		
		
		//TODO: Actual call to service. For the purpose of the demo creating a dummy User object		
		
		User user = null;
		if ("guest".equalsIgnoreCase(userName) && 
			"guest".equalsIgnoreCase(password)) {

			//making this user password not expiring within 30 days
			Calendar expCal = Calendar.getInstance();
			expCal.add(Calendar.DATE, 30);
			
			//dummy user object
			user = new User(userName, 
							"Salma", 
							"Hayek", 
							cal.getTime(), 
							"salmahayek@anonymous.com",
							expCal.getTime());
			
		} else if ("expired".equalsIgnoreCase(userName) && 
			"expired".equalsIgnoreCase(password)) {

			//making this user password expire
			Calendar expCal = Calendar.getInstance();
			expCal.add(Calendar.DATE, -2);

			//dummy user object
			user = new User(userName, 
							"Salma", 
							"Hayek", 
							cal.getTime(), 
							"salmahayek@anonymous.com",
							expCal.getTime());
			
		} else {
			throw new AppSvcException("Invalid username/password");
		}
		
		return user;
	}

	
	
}
