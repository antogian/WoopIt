package gr.teicm.icd.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gr.teicm.icd.data.entities.Message;
import gr.teicm.icd.data.services.*;

@Controller
public class MessageController {
	
	private List<Message> allMessages = new ArrayList<>();
	
	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String displayAllMessages(Model model)
	{
		this.allMessages = this.messageService.getAllMessages();
		model.addAttribute("allMessages", this.allMessages);
		return "home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String postMessage(@RequestParam("message") String msg, Model model)
	{
		Message newMessage = new Message();
		newMessage.setBody(msg);
		newMessage.getSender().setUserName(this.userService.getLoggedInUsername());
		Long id = this.messageService.getUserIdByUserName(newMessage.getSender().getUserName());
		this.messageService.insertMessage(newMessage, id);
		this.allMessages = this.messageService.getAllMessages();
		model.addAttribute("allMessages", this.allMessages);
		return "redirect:/home";
	}
}
