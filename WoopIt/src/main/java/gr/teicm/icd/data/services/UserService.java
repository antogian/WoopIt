package gr.teicm.icd.data.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import gr.teicm.icd.dao.UserDAO;
import gr.teicm.icd.data.entities.User;

public class UserService {
	
	public UserService(){

	}
	
	public Boolean insertUser(User user){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
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
	
    public String getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return name;
    }
    
    public void insertPhotoPath(User user) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    	
    	userDAO.insertPhotoPath(user);
    }
    
    public String getPhotoPath(String userName){
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    	
    	String photoPath = null;
    	photoPath = userDAO.getPhotoPath(userName);
    	return photoPath;
    }
    
    public void removePhoto(User user){
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    	
    	userDAO.removePhoto(user);
    }
}
