package gr.teicm.icd.dao;

import java.util.List;

import gr.teicm.icd.data.entities.Message;
import gr.teicm.icd.data.entities.User;

public interface MessageDAO {
	
	public void insertMessage(Message message);
	
	public List<Message> getAllMessages();
	
}
