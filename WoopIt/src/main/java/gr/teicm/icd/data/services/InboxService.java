package gr.teicm.icd.data.services;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import gr.teicm.icd.dao.InboxDAO;
import gr.teicm.icd.data.entities.Inbox;
import gr.teicm.icd.data.entities.User;

@Service
public class InboxService {
	
	public InboxService() {
		
	}
	
	public void insertInbox(Inbox pm){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			InboxDAO inboxDAO =  (InboxDAO) context.getBean("inboxDAO");
			inboxDAO.insertInbox(pm);
		}
	}
	
	public List<Inbox> getInbox(User user){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			InboxDAO inboxDAO =  (InboxDAO) context.getBean("inboxDAO");
			
			return inboxDAO.getInbox(user);
		}
	}
	
	public List<Inbox> getHistory(User user){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			InboxDAO inboxDAO =  (InboxDAO) context.getBean("inboxDAO");
			
			return inboxDAO.getHistory(user);
		}
	}
	
	public int getNewInboxQuantity(User user){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			InboxDAO inboxDAO =  (InboxDAO) context.getBean("inboxDAO");
			
			return inboxDAO.getNewInboxQuantity(user);
		}
	}
	
	public void deleteInbox(Long id, User user){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			InboxDAO inboxDAO =  (InboxDAO) context.getBean("inboxDAO");
			
			inboxDAO.deleteInbox(id, user);
		}
	}
	
	public void resetNewInboxQuantity(User user){
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){
			InboxDAO inboxDAO =  (InboxDAO) context.getBean("inboxDAO");
			
			inboxDAO.resetNewInboxQuantity(user);
		}
	}
}
