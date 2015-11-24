package gr.teicm.icd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.services.*;

@ControllerAdvice()
public class GlobalControllerAdvice {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("currentUser")
	public User getCurrentUser(){
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		//User currentUser2 = new User();
		
		return currentUser;
	}
}
