package gr.teicm.icd.data.services;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import gr.teicm.icd.dao.*;
import gr.teicm.icd.data.entities.*;

@Service
public class FriendService {
	
	public FriendService(){
		
	}
	
	public void addUserToFriends(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			FriendDAO friendDAO = (FriendDAO) context.getBean("FriendDAO");
			BlockedDAO blockedDAO = (BlockedDAO) context.getBean("BlockedDAO");
			
			if(!friendDAO.isFriend(currentUser, targetUser)){
				if(blockedDAO.isBlocked(currentUser, targetUser)){
					blockedDAO.removeUserFromBlocked(currentUser, targetUser);	
				}
				friendDAO.addUserToFriends(currentUser, targetUser);
			}
		}
	}
	
	public void removeUserFromFriends(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			FriendDAO friendDAO = (FriendDAO) context.getBean("FriendDAO");
			
			if(!friendDAO.isFriend(currentUser, targetUser)){
				friendDAO.removeUserFromFriends(currentUser, targetUser);
			}
		}
	}
	
	public Boolean isFriend(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			FriendDAO friendDAO = (FriendDAO) context.getBean("FriendDAO");
			return friendDAO.isFriend(currentUser, targetUser);
		}
	}
	
	public List<User> getAllFriends(User currentUser){
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			FriendDAO friendDAO = (FriendDAO) context.getBean("FriendDAO");
			
			return friendDAO.getAllFriends(currentUser);
		}
	}
}
