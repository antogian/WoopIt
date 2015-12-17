package gr.teicm.icd.dao;

import java.util.List;
import gr.teicm.icd.data.entities.Message;
import gr.teicm.icd.data.entities.User;

public interface MessageDAO {
	
	public void insertMessage(Message message);
	
	public List<Message> getAllMessages();
	
	public void insertLike(Long messageId);
	
	public void insertDislike(Long messageId);
	
	public boolean checkIfLiked(User user, Long messageId);
	
	public void insertMessageUserLike(User user, Long messageId);
	
}