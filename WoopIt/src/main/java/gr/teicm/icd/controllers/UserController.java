package gr.teicm.icd.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//Register User
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String createUser()
	{
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String createUserPOST(@ModelAttribute User user, RedirectAttributes redirectAttributes)
	{
		boolean success = userService.insertUser(user);
		redirectAttributes.addFlashAttribute("userName", user.getUserName());
		if(success==true)
			return "redirect:registerSuccess";
		else
			return "redirect:registerFailed";
	}
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {

        return "login";
    }
 
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/user/login?logout";
    }
    
    @RequestMapping(value="/registerSuccess", method=RequestMethod.GET) 
    public String successView(HttpServletRequest request){ 

    	return "registerSuccess"; 
    }
    
    @RequestMapping(value="/registerFailed", method=RequestMethod.GET) 
    public String failedView(HttpServletRequest request){ 

    	return "registerFailed"; 
    }
    
    @RequestMapping(value="/viewprofile", method=RequestMethod.GET) 
    public String profileView(){ 

    	return "viewprofile"; 
    }
    
    @RequestMapping(value="/editprofile", method=RequestMethod.GET) 
    public String editProfileView(){ 

    	return "editprofile"; 
    }
    
    @RequestMapping(value="/friendlist", method=RequestMethod.GET) 
    public String friendlistView(){ 

    	return "friendlist"; 
    }
    
    @RequestMapping(value="/history", method=RequestMethod.GET) 
    public String historyView(){ 

    	return "history"; 
    }
    
    @RequestMapping(value="/settings", method=RequestMethod.GET) 
    public String settingsView(){ 
    	
    	return "settings"; 
    }
    
    @RequestMapping(value="/inbox", method=RequestMethod.GET) 
    public String inboxView(){ 

    	return "inbox"; 
    }
}
