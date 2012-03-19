package com.aref.webapp.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.aref.webapp.appSvc.ILoginAppSvc;
import com.aref.webapp.exception.AppSvcException;
import com.aref.webapp.model.User;
import com.aref.webapp.util.AppUtil;

/**
 * 
 * This method is modelled after spring class ProviderManager. This method 
 * iterates through the list of authentication providers and calls doAuthentication
 * on them. Since we only need to call our custom service, we had defined a custom
 * authentication manager which calls the actual service to do authentication.
 * 
 * What's the need to define this class? ProviderManager executes doAuthentication on
 * the provided authentication provider. For example if we take spring class DaoAuthenticationProvider
 * which upon doAuthentication call, calls the authenticate method of DaoAuthenticationProvider which  
 * returns the spring class UserDetails object. This UserDetails object contains the password for that
 * user which you can compare with the user entered password in the form. Sometimes passwords are 
 * note stored in the database. If you are provided with a service which validates
 * user name and password supplied to it, we have to write a custom authentication
 * manager like this.
 * 
 * 
 * @author chandra
 *
 */
public class CustomAuthenticationManager extends AbstractAuthenticationManager {

	@Autowired
	private ILoginAppSvc loginAppSvc;
	
	@Override
	protected Authentication doAuthentication(Authentication authentication)
			throws AuthenticationException {

		if (!(authentication instanceof CustomAuthenticationToken)) {			
			throw new IllegalArgumentException("Only CustomAuthenticationManager is supported");
		}
		
		CustomAuthenticationToken authenticationToken = (CustomAuthenticationToken) authentication;		

		final String userName = (String) authenticationToken.getPrincipal();				
		final String password = (String) authenticationToken.getCredentials();		
		final String company = (String) authenticationToken.getCompany();
		
		if (AppUtil.isNullOrEmpty(userName) ||
			AppUtil.isNullOrEmpty(password) ||
			AppUtil.isNullOrEmpty(company)) {
			
			throw new BadCredentialsException("Invalid username/password");
		}
		
		User user = null;
		
		try {			
			
			//Actual service call
			user = loginAppSvc.login(userName, password, company);
			
		} catch (AppSvcException ase) {
			throw new BadCredentialsException(ase.getMessage());
		}
		
		List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<GrantedAuthority>();
		grantedAuthoritiesList.add(new GrantedAuthorityImpl("ROLE_USER"));
		
		
		return createSuccesssAuthentication(user,
											authenticationToken,
											grantedAuthoritiesList);

	}

	protected Authentication createSuccesssAuthentication(Object principal, 
														  CustomAuthenticationToken authentication, 
														  List<GrantedAuthority> grantedAuthoritiesList) {

		CustomAuthenticationToken result = new CustomAuthenticationToken(principal, 
																		 authentication.getCredentials(), 
																		 (String) authentication.getCompany(),
																	   	 grantedAuthoritiesList);

		result.setDetails(authentication.getDetails());

		return result;
	}
	
	
}
