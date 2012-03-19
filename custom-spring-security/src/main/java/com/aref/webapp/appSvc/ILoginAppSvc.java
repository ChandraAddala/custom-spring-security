package com.aref.webapp.appSvc;

import com.aref.webapp.exception.AppSvcException;
import com.aref.webapp.model.User;

public interface ILoginAppSvc {

	public User login(String userName, String password, String company) throws AppSvcException;
	
}
