package gr.teicm.icd.controllers;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gr.teicm.icd.data.entities.Inbox;
import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.InboxService;
import gr.teicm.icd.data.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private InboxService inboxService;
	
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
    
    @RequestMapping(value="/editprofile", method=RequestMethod.GET) 
    public String editProfileView(){ 

		//User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		//model.addAttribute("currentUser", currentUser);
    	
    	return "editprofile"; 
    }
    
    @RequestMapping(value="/history", method=RequestMethod.GET) 
    public String historyView(){ 

		//User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		//model.addAttribute("currentUser", currentUser);
    	
    	return "history"; 
    }
    
    @RequestMapping(value="/settings", method=RequestMethod.GET) 
    public String settingsView(){ 

		//User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		//model.addAttribute("currentUser", currentUser);
    	
    	return "settings"; 
    }
    
    @RequestMapping(value="/inbox", method=RequestMethod.GET) 
    public String inboxView(Model model, @RequestParam(value="delete", required = false) boolean delete, @RequestParam(value="id", required = false) Long id){ 
    	User user = userService.getUserByName(this.userService.getLoggedInUsername());
    	List<Inbox> allInbox = new ArrayList<>();
    	allInbox = inboxService.getInbox(user);
    	model.addAttribute("allInbox", allInbox);
    	//delete
    	if(delete==true && id>=0){
    		inboxService.deleteInbox(id, user);
    		return "inboxDeleted";
    	}
    	//reset new inbox messages notification
    	inboxService.resetNewInboxQuantity(user);
    	return "inbox"; 
    }
    
    @RequestMapping(value="/inboxNew", method=RequestMethod.GET)
    public String inboxNew(Model model){
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		//model.addAttribute("currentUser", currentUser);
		List<User> allFriends = new ArrayList<>();
		allFriends = this.userService.getAllFriends(currentUser);
		model.addAttribute("allFriends", allFriends);
		
    	return "inboxNew";
    }
    
    @RequestMapping(value="/inboxSend", method=RequestMethod.GET)
    public String inboxSend(@RequestParam("name") String name, Model model){
    	model.addAttribute("name", name);
    	return "inboxSend";
    }
    
    @RequestMapping(value="/inboxSend", method=RequestMethod.POST)
    public String inboxSendPOST(@RequestParam("receiverUser") String receiverUser, @RequestParam("body") String body, RedirectAttributes redirectAttributes){
    	System.out.println(receiverUser);
    	Inbox pm = new Inbox();
    	pm.setBody(body);
    	pm.setSenderUser(userService.getUserByName(userService.getLoggedInUsername()));
    	pm.setReceiverUser(userService.getUserByName(receiverUser));
    	inboxService.insertInbox(pm);
    	redirectAttributes.addFlashAttribute("userName", pm.getReceiverUser().getUserName());
    	return "redirect:inboxSent";
    }
    
    @RequestMapping(value="/inboxHistory", method=RequestMethod.GET)
    public String inboxHistory(Model model) {
    	User user = userService.getUserByName(this.userService.getLoggedInUsername());
    	List<Inbox> allHistory = new ArrayList<>();
    	allHistory = inboxService.getHistory(user);
    	model.addAttribute("allHistory", allHistory);
    	return "inboxHistory";
    }
    
    @RequestMapping(value="/inboxRequests", method=RequestMethod.GET)
    public String inboxRequests() {
    	return "inboxRequests";
    }
    
    @RequestMapping(value="/inboxDeleted", method=RequestMethod.GET)
    public String inboxDeleted() {
    	return "inboxDeleted";
    }
    
    @RequestMapping(value="/inboxSent", method=RequestMethod.GET)
    public String inboxSent() {
    	return "inboxSent";
    }
}
