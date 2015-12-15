package gr.teicm.icd.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.services.*;

@Controller
public class MessageController {
	
	private List<Message> allMessages = new ArrayList<>();
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	@Autowired
	private BlockedService blockedService;

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String homeView()
	{
		return "home";
	}
	
	@RequestMapping(value="/subHome", method=RequestMethod.GET)
	public ModelAndView displayAllMessages(Model model)
	{
		this.allMessages = this.messageService.getAllMessages();
		User currentUser = this.userService.getUserByName(this.userService.getLoggedInUsername());
		User targetUser = new User();
		
		for(int i = 0; i < this.allMessages.size(); i++)
		{
			targetUser = this.allMessages.get(i).getSender();
			if(this.blockedService.isBlocked(currentUser, targetUser))
			{
				this.allMessages.remove(i);
			}
		}
		
		model.addAttribute("allMessages", this.allMessages);
		//return "home";
		return new ModelAndView("subHome");
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public void postMessage(@RequestParam("message") String msg, Model model)
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
		//return "redirect:/home";
	}
	
	@RequestMapping(value="/like", method=RequestMethod.GET)
	public String msgLike(@RequestParam("msgId") long messageId, @RequestParam("like") boolean like){
		
		User user = new User();
		user = this.userService.getUserByName(this.userService.getLoggedInUsername());
		
		if(!messageService.checkIfLiked(user, messageId)){
			if(like){
				messageService.insertLike(messageId);
				messageService.insertMessageUserLike(user, messageId);
			}
			if(!like){
				messageService.insertDislike(messageId);
				messageService.insertMessageUserLike(user, messageId);
			}
		}

		return "redirect:/home";
	}
}