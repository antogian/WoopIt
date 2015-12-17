package gr.teicm.icd.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.services.*;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InboxService inboxService;
	
	@RequestMapping("/")
	public String goIndex()
	{
		return "welcome";
	}
	
	@RequestMapping("/welcome")
	public String goWelcome()
	{
		return "welcome";
	}
	
	@RequestMapping("/howto")
	public String goHowto()
	{
		return "howto";
	}
	
	@RequestMapping("/support")
	public String goSupport()
	{
		return "support";
	}
	
	@RequestMapping("/error")
	public String goError()
	{
		return "error";
	}
	
	@RequestMapping(value="/searchResults", method=RequestMethod.POST)
	public String goSearch(@RequestParam("keyword") String key, Model model)
	{	
		List<User> matchedUsers = new ArrayList<>();
		
		if(this.userService.userExists(key)){
			matchedUsers = this.userService.searchUsersByKeyword(key);
			model.addAttribute("matchedUsers", matchedUsers);
		}
		model.addAttribute("keyword", key);
		
		return "searchResults";
	}
	
    
	@RequestMapping(value="/subNav", method=RequestMethod.GET)
	public ModelAndView getNewNotifications(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		if (!(auth instanceof AnonymousAuthenticationToken)) { 
			int n = inboxService.getNewInboxQuantity(this.userService.getUserByName(userService.getLoggedInUsername()));
			model.addAttribute("newNotifications", n);
		}
		else{
			int n = 0;
			model.addAttribute("newNotifications", n);
		}
		return new ModelAndView("/subNav");	
	}
}
