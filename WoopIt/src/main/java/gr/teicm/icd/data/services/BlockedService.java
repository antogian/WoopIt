package gr.teicm.icd.data.services;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import gr.teicm.icd.dao.*;
import gr.teicm.icd.data.entities.*;

@Service
public class BlockedService {
	
	public BlockedService(){
		
	}
	
	public void addUserToBlocked(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			BlockedDAO blockedDAO = (BlockedDAO) context.getBean("BlockedDAO");
			FriendDAO friendDAO = (FriendDAO) context.getBean("FriendDAO");
			
			if(!blockedDAO.isBlocked(currentUser, targetUser)){
				if(friendDAO.isFriend(currentUser, targetUser)){
					friendDAO.removeUserFromFriends(currentUser, targetUser);	
				}
				blockedDAO.addUserToBlocked(currentUser, targetUser);
			}
		}
	}
	
	public void removeUserFromBlocked(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			BlockedDAO blockedDAO = (BlockedDAO) context.getBean("BlockedDAO");
			
			if(!blockedDAO.isBlocked(currentUser, targetUser)){
				blockedDAO.removeUserFromBlocked(currentUser, targetUser);
			}
		}
	}
	
	public Boolean isBlocked(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			BlockedDAO blockedDAO = (BlockedDAO) context.getBean("BlockedDAO");
			
			return blockedDAO.isBlocked(currentUser, targetUser);
		}
	}
	
	public List<User> getAllBlocked(User currentUser){
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			BlockedDAO blockedDAO = (BlockedDAO) context.getBean("BlockedDAO");
			
			return blockedDAO.getAllBlocked(currentUser);
		}
	}
	
}
