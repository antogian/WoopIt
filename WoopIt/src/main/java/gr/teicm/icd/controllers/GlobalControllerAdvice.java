package gr.teicm.icd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.services.*;

@ControllerAdvice()
public class GlobalControllerAdvice {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InboxService inboxService;
	
	@ModelAttribute("currentUser")
	public User getCurrentUser(){
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		//User currentUser2 = new User();
		
		return currentUser;
	}
	
	@ModelAttribute("newNotifications")
	public int getNewNotifications(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		if (!(auth instanceof AnonymousAuthenticationToken)) { 
			return inboxService.getNewInboxQuantity(getCurrentUser());
		}
		else
			return 0;
	}
}
