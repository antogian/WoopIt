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
    public String goProfileView(@RequestParam("name") String name, @RequestParam("friend") String friend, @RequestParam("unwanted") String unwanted, Model model){ 
    	
    	User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
    	
    	if (name.equals(currentUser.getUserName())){
        	model.addAttribute("user", currentUser);
        	model.addAttribute("isCurrentUser", true);
        	
        	return "viewprofile"; 
    	}
    	else{
        	User targetUser = this.userService.getUserByName(name);
    		model.addAttribute("user", targetUser);
        	model.addAttribute("isCurrentUser", false);
        	
        	if(friend.equals("true") && unwanted.equals("false")){
        		this.userService.addUserToFriends(currentUser, targetUser);
        		return "viewprofile"; 
        	}
        	if(friend.equals("false") && unwanted.equals("true")){
        		//this.userService.isFriend(currentUser, targetUser);
        		this.userService.addUserToUnwanted(currentUser, targetUser);
        		return "viewprofile"; 
        	}
        	
        	return "viewprofile";
    	}
    }
}
