package gr.teicm.icd.data.services;

import java.util.*;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import gr.teicm.icd.dao.UserDAO;
import gr.teicm.icd.data.entities.User;

@Service 
public class UserService {
	
	public UserService(){

	}
	
	public Boolean insertUser(User user){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
	    	UserDAO userDAO = (UserDAO) context.getBean("userDAO");
	    	
	    	// Check if the username already exist in the DB
	    	if(userDAO.checkIfUserNameExist(user.getUserName())==false){
	    		userDAO.insert(user);
	    		userDAO.insertRole(user);
	    		return true;
	    	}
	    	else
	    		return false;
		}
	}
	
	public User getUserByName(String userName){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			return userDAO.getUserByName(userName);
		}
	}
	
    public String getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return name;
    }
    
    public void insertPhotoPath(User user) {
    	try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
	    	UserDAO userDAO = (UserDAO) context.getBean("userDAO");
	    	
	    	userDAO.insertPhotoPath(user);
    	}
    }
    
    public String getPhotoPath(String userName){
    	try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
	    	UserDAO userDAO = (UserDAO) context.getBean("userDAO");
	    	
	    	String photoPath = null;
	    	photoPath = userDAO.getPhotoPath(userName);
	    	return photoPath;
    	}
    }
    
    public void removePhoto(User user){
    	try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
	    	UserDAO userDAO = (UserDAO) context.getBean("userDAO");
	
	    	userDAO.removePhoto(user);
    	}
    }
    
	public void addUserToFriends(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			
			if(!userDAO.isFriend(currentUser, targetUser)){
				if(userDAO.isBlocked(currentUser, targetUser)){
					userDAO.removeUserFromBlocked(currentUser, targetUser);	
				}
				userDAO.addUserToFriends(currentUser, targetUser);
			}
		}
	}
	
	public void addUserToBlocked(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			
			if(!userDAO.isBlocked(currentUser, targetUser)){
				if(userDAO.isFriend(currentUser, targetUser)){
					userDAO.removeUserFromFriends(currentUser, targetUser);	
				}
				userDAO.addUserToBlocked(currentUser, targetUser);
			}
		}
	}
	
	public void removeUserFromFriends(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			
			if(!userDAO.isFriend(currentUser, targetUser)){
				userDAO.removeUserFromFriends(currentUser, targetUser);
			}
		}
	}
	
	public void removeUserFromBlocked(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			
			if(!userDAO.isBlocked(currentUser, targetUser)){
				userDAO.removeUserFromBlocked(currentUser, targetUser);
			}
		}
	}
	
	public Boolean isFriend(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			return userDAO.isFriend(currentUser, targetUser);
		}
	}
	
	public Boolean isBlocked(User currentUser, User targetUser){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			return userDAO.isBlocked(currentUser, targetUser);
		}
	}
	
	public List<User> getAllFriends(User currentUser){
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			
			return userDAO.getAllFriends(currentUser);
		}
	}
	
	public List<User> getAllBlocked(User currentUser){
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			
			return userDAO.getAllBlocked(currentUser);
		}
	}
	
	public User getUserById(Long userId){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			return userDAO.getUserById(userId);
		}
	}
}
