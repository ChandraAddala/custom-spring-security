package com.aref.webapp.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aref.webapp.ui.model.ChangePassword;

@Controller
public class ChangePasswordController {

	@RequestMapping(value="/changePassword", method=RequestMethod.GET)
	public String displayChangePassword(Map<String,Object> model) {
		
		model.put("changePassword", new ChangePassword());
		
		return "changePassword";
	}

	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	public String changePassword(Map<String,Object> model, ChangePassword changePassword) {
		
		//Assuming successful password updation. You would call the actual service to 
		//change password here.
		
		return "welcome";
	}
}
