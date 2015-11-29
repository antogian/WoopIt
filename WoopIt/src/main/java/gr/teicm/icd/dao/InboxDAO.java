package gr.teicm.icd.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import gr.teicm.icd.data.entities.Inbox;
import gr.teicm.icd.data.entities.User;
@Repository
public interface InboxDAO {
	
	 public void insertInbox(Inbox pm);
	 
	 public List<Inbox> getInbox(User user);
	 
	 public List<Inbox> getHistory(User user);
	 
	 public int getNewInboxQuantity(User user);
	 
	 public void deleteInbox(Long id, User user);
	 
	 public void resetNewInboxQuantity(User user);
	 
	 public User getUserById (Long userId);
}
