package com.aref.webapp.exception;

public class AppSvcException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AppSvcException(String message) {
		super(message);
	}

	public AppSvcException(String message, Exception e) {
		super(message, e);
	}
	
}
