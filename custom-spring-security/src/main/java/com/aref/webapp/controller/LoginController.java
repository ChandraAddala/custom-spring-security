package com.aref.webapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aref.webapp.util.AppConstants;

@Controller
public class LoginController {

	@RequestMapping(value={"/","/login"}, method=RequestMethod.GET)
	public String printWelcome(ModelMap model,
							   HttpSession session,
							   @RequestParam(value="error",required=false) String error) {
 

		AuthenticationException e = (AuthenticationException) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

		String errorMessage = "Invalid username/password";		
		if (e != null) {
			errorMessage = e.getMessage();
		}
		
		//Custom error message for invalid login
		model.put(AppConstants.ERRORMESSAGE, errorMessage);
		model.put(AppConstants.ERROR, error);
		
		return "login";
	}
	
}
