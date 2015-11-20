package gr.teicm.icd.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.*;

@Controller
public class MessageController {
	
	private List<Message> allMessages = new ArrayList<>();
	
	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
	@Autowired
	GeolocationService geolocationService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String displayAllMessages(Model model)
	{
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		model.addAttribute("currentUser", currentUser);
		
		this.allMessages = this.messageService.getAllMessages();
		model.addAttribute("allMessages", this.allMessages);
		return "home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String postMessage(@RequestParam("message") String msg, Model model)
	{
		Message newMessage = new Message();
		User user = new User();
		user = this.userService.getUserByName(this.userService.getLoggedInUsername());

		newMessage.setBody(msg);
		newMessage.setSender(this.userService.getUserByName(this.userService.getLoggedInUsername()));
		newMessage.setMessageLatitude(user.getUserLatitude());
		newMessage.setMessageLongitude(user.getUserLongitude());
		newMessage.setMessageRadius(user.getUserRadius());
		
		this.messageService.insertMessage(newMessage);
		this.allMessages = this.messageService.getAllMessages();
		model.addAttribute("allMessages", this.allMessages);
		return "redirect:/home";
	}
}
