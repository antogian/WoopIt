package gr.teicm.icd.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
    
	@RequestMapping(value="/viewprofile", method=RequestMethod.GET) 
    public String goProfileView(@RequestParam("name") String name, @RequestParam("friend") String friend, @RequestParam("blocked") String blocked, Model model){ 
    	
    	User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
    	//model.addAttribute("currentUser", currentUser);
    	
    	if (name.equals(currentUser.getUserName())){
        	model.addAttribute("targetUser", currentUser);
        	
        	return "viewprofile"; 
    	}
    	else{
        	User targetUser = this.userService.getUserByName(name);
    		model.addAttribute("targetUser", targetUser);
        	
        	if(friend.equals("true") && blocked.equals("false")){
        		this.userService.addUserToFriends(currentUser, targetUser);
        		return "viewprofile"; 
        	}
        	if(friend.equals("false") && blocked.equals("true")){
        		this.userService.addUserToBlocked(currentUser, targetUser);
        		return "viewprofile"; 
        	}
        	
        	return "viewprofile";
    	}
    }
	
	@RequestMapping(value="friendlist", method=RequestMethod.GET) 
	public String goFriendList(Model model){
		
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		//model.addAttribute("currentUser", currentUser);
		List<User> allFriends = new ArrayList<>();
		allFriends = this.userService.getAllFriends(currentUser);
		model.addAttribute("allFriends", allFriends);
		
		return "friendlist";
	}
	
	@RequestMapping(value="blacklist", method=RequestMethod.GET) 
	public String goUnwantedList(Model model){
		
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		//model.addAttribute("currentUser", currentUser);
		List<User> allBlocked = new ArrayList<>();
		allBlocked = this.userService.getAllBlocked(currentUser);
		model.addAttribute("allBlocked", allBlocked);
		
		return "blacklist";
	}
	
}
