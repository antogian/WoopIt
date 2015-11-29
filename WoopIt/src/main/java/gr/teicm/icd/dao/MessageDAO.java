package gr.teicm.icd.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import gr.teicm.icd.data.entities.Message;
@Repository
public interface MessageDAO {
	
	public void insertMessage(Message message);
	
	public List<Message> getAllMessages();
	
}
