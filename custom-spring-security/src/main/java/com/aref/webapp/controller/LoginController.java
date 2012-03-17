package com.aref.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aref.webapp.util.AppConstants;

@Controller
public class LoginController {

	@RequestMapping(value={"/welcome"}, method=RequestMethod.GET)
	public String printWelcome(ModelMap model) {
 
		model.addAttribute(AppConstants.MESSAGE, "Custom Spring Security Example - Login successful");
		
		return "welcome";
	}
	
}
