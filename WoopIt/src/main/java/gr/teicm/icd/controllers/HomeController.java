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
import gr.teicm.icd.data.services.MessageService;;

@Controller
public class HomeController {
	
	private List<Message> allMessages = new ArrayList<>();
	
	@Autowired
	private MessageService messageService;
	
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
	
	@RequestMapping("/home")
	public String goHome()
	{
		return "home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String displayAllMessages(Model model)
	{
		this.allMessages = this.messageService.getAllMessages();
		//System.out.println(this.allMessages.get(0).getContent());
		model.addAttribute("allMessages", this.allMessages);
		return "home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String postMessage(@RequestParam("message") String msg, Model model)
	{
		Message newMessage = new Message();
		newMessage.setContent(msg);
		
		this.messageService.insertMessage(newMessage);
		this.allMessages = this.messageService.getAllMessages();
		model.addAttribute("allMessages", this.allMessages);
		return "home";
	}
}
