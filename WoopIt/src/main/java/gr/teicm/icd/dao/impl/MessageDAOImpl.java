package gr.teicm.icd.dao.impl;

import javax.sql.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.*;
import gr.teicm.icd.dao.*;
import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.services.GeolocationService;
import gr.teicm.icd.data.services.UserService;

public class MessageDAOImpl implements MessageDAO{

	@Autowired
	UserService userService;
	@Autowired
	GeolocationService geolocationService;
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public void insertMessage(Message message){
		String sqlQuery = "INSERT INTO MESSAGES " + 
						"(MESSAGE_USER_ID, MESSAGE_BODY, MESSAGE_LATITUDE, MESSAGE_LONGITUDE, MESSAGE_RADIUS) " +
						"VALUES(?, ?, ?, ?, ?)";
		//Integer id = this.getIdFromDb(message.getSender());
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			
			ps.setString(1, Long.toString(message.getSender().getUserId()));
			ps.setString(2, message.getBody());
			ps.setDouble(3, message.getMessageLatitude());
			ps.setDouble(4, message.getMessageLongitude());
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
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Message msg = new Message();
				msg.setBody(rs.getString("MESSAGE_BODY"));
				Long id = rs.getLong("MESSAGE_USER_ID");
				msg.setMessageLatitude(rs.getDouble("MESSAGE_LATITUDE"));
				msg.setMessageLongitude(rs.getDouble("MESSAGE_LONGITUDE"));
				msg.setMessageRadius(rs.getInt("MESSAGE_RADIUS"));
				msg.setSender(userDAO.findByUserId(id));
				allMessages.add(msg);
			}
			ps.close();
			
			// Select the messages with coords near the user's location and put them in new list.
			double lat1,lat2,lng1,lng2;
			int rad1,rad2;
			List<Message> selectedMessages = new ArrayList<>();
			User user = new User();
			//user = userDAO.getUserByName(userService.getLoggedInUsername());
			user.setUserId(39L);
			user.setUserName("panos21");
			user.setUserLatitude(37.8619956000);
			user.setUserLongitude(23.7541813000);
			user.setUserRadius(100);
			
			lat1 = user.getUserLatitude();
			lng1 = user.getUserLongitude();
			rad1 = user.getUserRadius();
			
			for (Message msg : allMessages) {
				lat2 = msg.getMessageLatitude();
				lng2 = msg.getMessageLongitude();
				rad2 = msg.getMessageRadius();
				
				System.out.println(lat1 + " " + lng1 + " " + rad1 + " " + lat2 + " " + lng2 + " " + rad2 );//TA DATA YPARXOUN checked
				if(geolocationService.isOverlap(lat1, lat2, lng1, lng2, rad1, rad2)){
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

}