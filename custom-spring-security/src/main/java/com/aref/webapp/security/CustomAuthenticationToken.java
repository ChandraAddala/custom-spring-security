package com.aref.webapp.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * This is a custom token used by CustomAuthenticationManager for authenticating
 * an user. We could actually user UsernamePasswordAuthenticationToken which is a 
 * spring class. Reason for creating this is because of the extra custom parameter 
 * company. 
 * 
 * @author chandra
 *
 */
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private final String company;

	public CustomAuthenticationToken(Object principal, 
									 Object credentials, 
									 String company) {
		
		super(principal, credentials);
		this.company = company;
	}
	
	public CustomAuthenticationToken(Object principal, 
									 Object credentials, 
									 String company,
									 GrantedAuthority[] authorities) {
		
		super(principal, credentials, Arrays.asList(authorities));
		this.company = company;
	}
	
	public CustomAuthenticationToken(Object principal, 
								   	 Object credentials,
								   	 String company,
									 Collection<? extends GrantedAuthority> authorities) {
		
		super(principal, credentials, authorities);
		this.company = company;
	}

	public String getCompany() {
		return company;
	}
			
}
