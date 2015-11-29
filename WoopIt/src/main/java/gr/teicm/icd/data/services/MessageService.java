package gr.teicm.icd.data.services;

import java.util.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import gr.teicm.icd.dao.*;
import gr.teicm.icd.data.entities.*;
@Service
public class MessageService {
	
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