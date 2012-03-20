package com.aref.webapp.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import com.aref.webapp.model.User;
import com.aref.webapp.util.AppConstants;
import com.aref.webapp.util.AppUtil;

/**
 * Login forms must present two parameters to this filter: a username, 
 * password and company.
 * 
 * 
 * Custom Authentication filter for form based authentication. This is modelled after the spring 
 * provided filter for form based authentication(UsernamePasswordAuthenticationFilter). If you 
 * look at the source code of UsernamePasswordAuthenticationFilter, you would see a lot of 
 * similarity with this code.
 * 
 * @author chandra
 *
 */
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "j_password";
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
	
	private String usernameParameter = "j_username";
	private String passwordParameter = "j_password";
	
	// custom fields for ai security login
	private String companyParameter = "company";

	
	private boolean postOnly = true;
	
	public CustomAuthenticationFilter() {
		super("/j_spring_security_check");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {

		if ((this.postOnly) && (!(request.getMethod().equals("POST")))) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		
		String userName = AppUtil.trim(obtainUserID(request));
		String password = AppUtil.emptyIfNull(obtainPassword(request));
		String company = AppUtil.trim(obtainCompany(request));

		if (AppUtil.isNullOrEmpty(userName) || 
		    AppUtil.isNullOrEmpty(password) ||
		    AppUtil.isNullOrEmpty(company)) {
			
			throw new BadCredentialsException("Invalid username/password");
		}
		
		userName = userName.trim();
		
		//Using custom authentication token to include extra parameter company. Instead you can use UsernamePasswordAuthenticationToken
		CustomAuthenticationToken authRequest = new CustomAuthenticationToken(userName, password, company);

		// Place the last username attempted into HttpSession for views
		HttpSession session = request.getSession(false);
		if ((session != null) || (getAllowSessionCreation())) {
			session = request.getSession(); 
			session.setAttribute("SPRING_SECURITY_LAST_USERNAME", TextEscapeUtils.escapeEntities(userName));
		}
		
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		
		//calling authentication manager
		CustomAuthenticationToken authentication = (CustomAuthenticationToken) super.getAuthenticationManager().authenticate(authRequest);
		
		User user = (User) authentication.getPrincipal();
		session.setAttribute(AppConstants.USER, user);
		
		//if password expired
		if (!(new Date(System.currentTimeMillis()).before(user.getPasswordExpiryDate()))) {
			
			//changing the defaultTargetUrl to force user to change password.
			request.setAttribute(CustomSimpleUrlAuthenticationSuccessHandler.DEFAULT_TARGET_ATTRIBUTE, "/changePassword");			
		}

		
		return authentication;
	}
	
	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter(this.passwordParameter);
	}
	
	protected String obtainUserID(HttpServletRequest request) {
		return request.getParameter(this.usernameParameter);
	}
	
 	protected String obtainCompany(HttpServletRequest request) {
		return request.getParameter(this.companyParameter);
	}

	protected void setDetails(HttpServletRequest request, CustomAuthenticationToken authRequest) {
		authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
	}
	
}
