package gr.teicm.icd.dao;

import java.util.List;

import gr.teicm.icd.data.entities.Message;

public interface MessageDAO {
	
	public void insertMessage(Message message, Long id);
	
	public List<Message> getAllMessages();
	
	public Long getIdFromDb(String userName);
}
