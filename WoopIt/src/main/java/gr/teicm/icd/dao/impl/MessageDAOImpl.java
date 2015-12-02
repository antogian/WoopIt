package gr.teicm.icd.dao.impl;

import javax.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import gr.teicm.icd.dao.*;
import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.services.UserService;
import gr.teicm.icd.geolocation.impl.GeolocationImpl;
@Repository
public class MessageDAOImpl implements MessageDAO{
	
	@Autowired
	UserService userService;
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	
	public void insertMessage(Message message){
		String sqlQuery = "INSERT INTO MESSAGES " + 
						"(MESSAGE_USER_ID, MESSAGE_BODY, MESSAGE_LATITUDE, MESSAGE_LONGITUDE, MESSAGE_RADIUS) " +
						"VALUES(?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			
			ps.setString(1, Long.toString(message.getSender().getUserId()));
			ps.setString(2, message.getBody());
			ps.setDouble(3, message.getMessageLatitude());
			ps.setDouble(4, message.getMessageLongitude());
			if(message.getMessageRadius()>100)
				ps.setInt(5, 100);
			else
				ps.setInt(5, message.getMessageRadius());
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
		UserDAOImpl userDAO = new UserDAOImpl();
		userDAO.setDataSource(this.dataSource);
		GeolocationImpl geo = new GeolocationImpl();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Message msg = new Message();
				msg.setId(rs.getLong("MESSAGE_ID"));
				msg.setBody(rs.getString("MESSAGE_BODY"));
				Long id = rs.getLong("MESSAGE_USER_ID");
				msg.setMessageLatitude(rs.getDouble("MESSAGE_LATITUDE"));
				msg.setMessageLongitude(rs.getDouble("MESSAGE_LONGITUDE"));
				msg.setMessageRadius(rs.getInt("MESSAGE_RADIUS"));
				msg.setSender(userDAO.findByUserId(id));
				msg.setMessageLikes(rs.getInt("MESSAGE_LIKES"));
				msg.setMessageDislikes(rs.getInt("MESSAGE_DISLIKES"));
				allMessages.add(msg);
			}
			ps.close();
			
			// Select the messages with coords near the user's location and put them in new list.
			double lat1,lng1;
			int rad1;
			List<Message> selectedMessages = new ArrayList<>();
			User user = new User();
			
			//TODO: Use getLoggedInUsername method
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String name = auth.getName(); //get logged in username
	        
			user = userDAO.getUserByName(name);
			
			lat1 = user.getUserLatitude();
			lng1 = user.getUserLongitude();
			rad1 = user.getUserRadius();
			
			for (Message msg : allMessages) {
				double lat2 = msg.getMessageLatitude();
				double lng2 = msg.getMessageLongitude();
				int rad2 = msg.getMessageRadius();
				
				if(geo.isOverlap(lat1, lat2, lng1, lng2, rad1, rad2)){
					selectedMessages.add(msg);
				}
			}
			return selectedMessages;
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

	public void insertLike(Long messageId){
		String sqlQuery = "UPDATE MESSAGES SET MESSAGE_LIKES=MESSAGE_LIKES+1 WHERE MESSAGE_ID=?";
		Connection conn = null;
		UserDAOImpl userDAO = new UserDAOImpl();
		userDAO.setDataSource(this.dataSource);
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setLong(1, messageId);
			ps.executeUpdate();
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
	
	public void insertDislike(Long messageId){
		String sqlQuery = "UPDATE MESSAGES SET MESSAGE_DISLIKES=MESSAGE_DISLIKES+1 WHERE MESSAGE_ID=?";
		Connection conn = null;
		UserDAOImpl userDAO = new UserDAOImpl();
		userDAO.setDataSource(this.dataSource);
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setLong(1, messageId);
			ps.executeUpdate();
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
	
	public void insertMessageUserLike(User user, Long messageId){
		String sqlQuery = "INSERT INTO MESSAGE_LIKES " + 
				"(MESSAGE_LIKE_MSG_ID, MESSAGE_LIKE_USER_ID) " +
				"VALUES(?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			
			ps.setLong(1, messageId);
			ps.setLong(2, user.getUserId());
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
	
	public boolean checkIfLiked(User user, Long messageId){
		String sql = "SELECT * FROM MESSAGE_LIKES WHERE MESSAGE_LIKE_MSG_ID=? AND MESSAGE_LIKE_USER_ID = ?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, messageId);
			ps.setLong(2, user.getUserId());
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				rs.close();
				ps.close();
				return true;
			}
			else{
				rs.close();
				ps.close();
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} 
			catch (SQLException e) {}
			}
		}		
	}
}