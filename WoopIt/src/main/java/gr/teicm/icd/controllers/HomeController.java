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
	
    @RequestMapping(value="/viewprofile", method=RequestMethod.GET) 
    public String goProfileView(@RequestParam("name") String name, Model model){ 

    	if (name.equals(this.userService.getLoggedInUsername())){
        	User currentUser = this.userService.getUserByName(name);
        	model.addAttribute("currentUser", currentUser);
        	model.addAttribute("isLoggedIn", true);
    	}
    	else{
        	User currentUser = this.userService.getUserByName(name);
        	model.addAttribute("currentUser", currentUser);
        	model.addAttribute("isLoggedIn", false);
    	}
    	
    	return "viewprofile"; 
    }
}
