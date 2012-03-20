package com.aref.webapp.security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 
 * This class is modelled after AbstractAuthenticationTargetUrlRequestHandler. Just altering the redirect behaviour
 * after successful login by overriding determineTargetUrl and isAlwaysUseDefaultTargetUrl methods.
 * 
 * @author chandra
 *
 */
public class CustomSimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	public static String DEFAULT_TARGET_ATTRIBUTE = "redirecturl";
	private String targetUrlAttribute = DEFAULT_TARGET_ATTRIBUTE;

	
	public CustomSimpleUrlAuthenticationSuccessHandler() {
		
	}
	
	public CustomSimpleUrlAuthenticationSuccessHandler(String defaultTargetUrl) {
		super(defaultTargetUrl);
		
		//For redirecting based on the request parameter redirecturl
		setTargetUrlParameter("redirecturl");
		
		//This has been set to true so that spring can refer the location of the target url from http header "Referer"
		//setUseReferer(true);
	}
	
	
	/**
	 * 
	 * This method has been overridden to check request attribute before checking 
	 * request parameter/http header.
	 * 
	 * @param request
	 * @param respose
	 * @return 
	 */
	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		String targetUrl = this.getDefaultTargetUrl();

		targetUrl = (String) request.getAttribute(this.targetUrlAttribute);
		
		if (StringUtils.hasText(targetUrl)) {
			try {
				targetUrl = URLDecoder.decode(targetUrl, "UTF-8");
			}catch (UnsupportedEncodingException e) {
				throw new IllegalStateException("UTF-8 not supported. Shouldn't be possible");
			}
			
			return targetUrl;
		}

		return super.determineTargetUrl(request, response);
	}
	
	/**
	 * We are overriding alwaysUseDefaultTargetUrl to false by overriding this method.
	 * 
	 * @return false
	 */
	@Override
	protected boolean isAlwaysUseDefaultTargetUrl()  {		
		return false;
	}
	
	public void setTargetUrlAttribute(String targetUrlAttribute) {
		Assert.hasText("targetUrlAttribute canot be null or empty");
		this.targetUrlAttribute = targetUrlAttribute;
	}
	
}
