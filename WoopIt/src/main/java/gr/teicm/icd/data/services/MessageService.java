package gr.teicm.icd.data.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import gr.teicm.icd.dao.*;
import gr.teicm.icd.data.entities.*;

public class MessageService {

	@Autowired
	GeolocationService geolocationService;
	@Autowired
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