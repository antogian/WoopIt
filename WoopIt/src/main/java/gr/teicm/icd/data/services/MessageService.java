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
	
	public void deleteMessage(Message message){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			MessageDAO messageDAO =  (MessageDAO) context.getBean("messageDAO");
			messageDAO.deleteMessage(message);
		}
	}
	
	public void insertLike(Long messageId){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			MessageDAO messageDAO =  (MessageDAO) context.getBean("messageDAO");
			messageDAO.insertLike(messageId);
		}		
	}
	
	public void insertDislike(Long messageId){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			MessageDAO messageDAO =  (MessageDAO) context.getBean("messageDAO");
			messageDAO.insertDislike(messageId);
		}		
	}
	
	public boolean checkIfLiked(User user, Long messageId){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			MessageDAO messageDAO = (MessageDAO) context.getBean("messageDAO");
			
			return messageDAO.checkIfLiked(user, messageId);
		}		
	}
	
	public void insertMessageUserLike(User user, Long messageId){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			MessageDAO messageDAO =  (MessageDAO) context.getBean("messageDAO");
			messageDAO.insertMessageUserLike(user, messageId);
		}		
	}
}