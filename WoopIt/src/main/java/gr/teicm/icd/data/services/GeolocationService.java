package gr.teicm.icd.data.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import gr.teicm.icd.dao.UserDAO;
import gr.teicm.icd.data.entities.User;
@Service
public class GeolocationService {
	
	public void insertGeoLocation(User user){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
	    	UserDAO userDAO = (UserDAO) context.getBean("userDAO");
	    	userDAO.insertGeoLocation(user);
		}
	}
	
	public User getGeoLocation(String userName) {
    	try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
	    	UserDAO userDAO = (UserDAO) context.getBean("userDAO");
	    	return userDAO.getGeoLocation(userName);
    	}
	}
}
