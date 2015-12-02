package gr.teicm.icd.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.services.*;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
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
}
