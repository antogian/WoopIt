package gr.teicm.icd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import gr.teicm.icd.dao.MessageDAO;
import gr.teicm.icd.data.entities.Message;

public class MessageDAOImpl implements MessageDAO{

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	//TODO MESSAGE_USER_ID must be fetched by Sender attribute of Message
	public void insertMessage(Message message){
		String sqlQuery = "INSERT INTO MESSAGES " + 
						"(MESSAGE_USER_ID, MESSAGE_BODY) " +
						"VALUES(?, ?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, "19");
			ps.setString(2, message.getContent());
			ps.executeUpdate();
			ps.close();
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}
		finally{
			if (conn != null){
				try{
					conn.close();
				}
				catch(SQLException e){
					System.out.println("");
				}
			}
		}
	}
	
	public List<Message> getAllMessages(){
		
		List<Message> allMessages = new ArrayList<>();
		String sqlQuery = "SELECT * FROM MESSAGES";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ResultSet data = ps.executeQuery();
			while(data.next()){
				Message msg = new Message();
				msg.setContent(data.getString("MESSAGE_BODY"));
				allMessages.add(msg);
			}
			ps.close();
			return allMessages;
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}
		finally{
			if (conn != null){
				try{
					conn.close();
				}
				catch(SQLException e){
					System.out.println("");
				}
			}
		}
		
	}

}
