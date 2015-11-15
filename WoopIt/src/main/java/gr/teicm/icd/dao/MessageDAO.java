package gr.teicm.icd.dao;

import java.util.List;

import gr.teicm.icd.data.entities.Message;

public interface MessageDAO {
	
	public void insertMessage(Message message);
	
	public List<Message> getAllMessages();
}
