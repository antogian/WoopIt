package gr.teicm.icd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.UserService;

@Controller
public class HomeController {
	

	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String goIndex(Model model)
	{
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		model.addAttribute("currentUser", currentUser);
		
		return "welcome";
	}
	
	@RequestMapping("/welcome")
	public String goWelcome(Model model)
	{
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		model.addAttribute("currentUser", currentUser);
		
		return "welcome";
	}
	
	@RequestMapping("/howto")
	public String goHowto(Model model)
	{
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		model.addAttribute("currentUser", currentUser);
		
		return "howto";
	}
	
	@RequestMapping("/support")
	public String goSupport(Model model)
	{
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		model.addAttribute("currentUser", currentUser);
		
		return "support";
	}
}
