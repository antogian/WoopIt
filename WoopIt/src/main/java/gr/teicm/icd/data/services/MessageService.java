package gr.teicm.icd.data.services;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import gr.teicm.icd.dao.*;
import gr.teicm.icd.data.entities.*;

public class MessageService {

	
	public MessageService(){
		
	}
	
	public void insertMessage(Message message, Long id){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			MessageDAO messageDAO =  (MessageDAO) context.getBean("messageDAO");
			messageDAO.insertMessage(message, id);
		}
	}
	
	public List<Message> getAllMessages(){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			MessageDAO messageDAO = (MessageDAO) context.getBean("messageDAO");
			return messageDAO.getAllMessages();
		}
	}
	
	public Long getIdFromDb(String userName){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			MessageDAO messageDAO = (MessageDAO) context.getBean("messageDAO");
			return messageDAO.getIdFromDb(userName);
		}
	}
}