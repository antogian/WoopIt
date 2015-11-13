package gr.teicm.icd.dao.impl;

import javax.sql.*;
import java.sql.*;
import java.util.*;
import gr.teicm.icd.dao.*;
import gr.teicm.icd.data.entities.*;

public class MessageDAOImpl implements MessageDAO{

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public void insertMessage(Message message, Long id){
		String sqlQuery = "INSERT INTO MESSAGES " + 
						"(MESSAGE_USER_ID, MESSAGE_BODY) " +
						"VALUES(?, ?)";
		//Integer id = this.getIdFromDb(message.getSender());
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			
			ps.setString(1, Long.toString(id));
			ps.setString(2, message.getBody());
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
		UserDAOImpl userDao = new UserDAOImpl();
		userDao.setDataSource(this.dataSource);
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Message msg = new Message();
				msg.setBody(rs.getString("MESSAGE_BODY"));
				Long id = rs.getLong("MESSAGE_USER_ID");
				msg.setSender(userDao.findByUserId(id));
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
	
	public Long getIdFromDb(String userName){
		String sqlQuery = "SELECT USER_ID FROM USERS WHERE USER_NAME = ?";
		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			Long userId = new Long(0);
			if(rs.next()){
				userId = rs.getLong("USER_ID");
				ps.close();
				rs.close();
				return userId;
			}
			ps.close();
			rs.close();
			return userId;
		}
		catch (SQLException e){
			throw new RuntimeException(e);
		}
		finally{
			if(conn != null){
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