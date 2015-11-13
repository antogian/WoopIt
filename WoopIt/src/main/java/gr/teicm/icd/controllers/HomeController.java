package gr.teicm.icd.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gr.teicm.icd.data.entities.Message;
import gr.teicm.icd.data.services.MessageService;
import gr.teicm.icd.data.services.UserService;;

@Controller
public class HomeController {
	
	private List<Message> allMessages = new ArrayList<>();
	
	@Autowired
	private MessageService messageService;
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
		Long id = this.messageService.getIdFromDb(newMessage.getSender().getUserName());
		this.messageService.insertMessage(newMessage, id);
		this.allMessages = this.messageService.getAllMessages();
		model.addAttribute("AllMessages", this.allMessages);
		return "redirect:/home";
	}
}
