package gr.teicm.icd.data.services;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import gr.teicm.icd.dao.*;
import gr.teicm.icd.data.entities.*;

public class MessageService {

	UserService userService;
	
	public MessageService(){
		
	}
	
	public void insertMessage(Message message){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			MessageDAO messageDAO =  (MessageDAO) context.getBean("messageDAO");
			messageDAO.insertMessage(message);
		}
	}
	
	public List<Message> getAllMessages(){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			MessageDAO messageDAO = (MessageDAO) context.getBean("messageDAO");
			return messageDAO.getAllMessages();
		}
	}
	
}