package gr.teicm.icd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gr.teicm.icd.data.entities.User;


public class UserDAOTest {
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://83.212.116.34:3306/testwoopit", System.getenv("WoopItUser"), System.getenv("WoopItPass"));
	}
	
	public User findByUserIdTest(double userId) throws SQLException {
		User user = new User();
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select * from USERS where USER_ID='"+ userId +"'");
		while (rs.next()) {
			user.setUserId(rs.getLong("USER_ID"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setUserLatitude(rs.getDouble("USER_LATITUDE"));
			user.setUserLongitude(rs.getDouble("USER_LONGITUDE"));
			user.setUserRadius(rs.getInt("USER_RADIUS"));
		}
		return user;
	}
	
	public User getUserByNameTest(String userName) throws SQLException {
		User user = new User();
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select * from USERS where USER_NAME='"+ userName +"'");
		while (rs.next()) {
			user.setUserId(rs.getLong("USER_ID"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setUserLatitude(rs.getDouble("USER_LATITUDE"));
			user.setUserLongitude(rs.getDouble("USER_LONGITUDE"));
			user.setUserRadius(rs.getInt("USER_RADIUS"));
		}
		return user;
	}
	
	public Boolean checkIfUserNameExistTest(String userName) throws SQLException {
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select * from USERS where USER_NAME='"+ userName +"'");
		
		if (rs.first()) {
			rs.close();
			return true;
		}
		else{
			rs.close();
			return false;
		}
	}
	
	public void insertPhotoPathTest(User user) throws SQLException {
		Statement stmt = getConnection().createStatement();
		String userPhotoPath = user.getUserPhotoPath();
		String userName = user.getUserName();
		stmt.executeUpdate("UPDATE USERS SET USER_PHOTO ='"+userPhotoPath+"' where USER_NAME='"+userName+"'");
		
	}
	
	public String getPhotoPathTest(String userName) throws SQLException {
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT USER_PHOTO FROM USERS WHERE USER_NAME ='"+ userName +"'");
		String userPhotoPath = null;
		while (rs.next()) {
			userPhotoPath = rs.getString("USER_PHOTO");
		}
		rs.close();
		return userPhotoPath;
	}
	
	public User getGeoLocationTest(String userName) throws SQLException {
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT USER_LATITUDE, USER_LONGITUDE, USER_RADIUS FROM USERS WHERE USER_NAME ='"+ userName +"'");
		
		User user = new User();
		if(rs.next()){
			user.setUserLatitude(rs.getDouble("USER_LATITUDE"));
			user.setUserLongitude(rs.getDouble("USER_LONGITUDE"));
			user.setUserRadius(rs.getInt("USER_RADIUS"));
		}
		rs.close();
		return user;
	}
	
	public Boolean isFriendTest(User currentUser, User targetUser) throws SQLException {
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM FRIENDS WHERE FRIENDS_CURRENT_USER_ID ='"+ currentUser.getUserId() +"' AND FRIENDS_TARGET_USER_ID='"+ targetUser.getUserId() +"'");
		
		if (rs.first()) {
			rs.close();
			return true;
		}
		else{
			rs.close();
			return false;
		}
	}
	
	public Boolean isBlockedTest(User currentUser, User targetUser) throws SQLException {
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM BLOCKED WHERE BLOCKED_CURRENT_USER_ID ='"+ currentUser.getUserId() +"' AND BLOCKED_TARGET_USER_ID='"+ targetUser.getUserId() +"'");
		
		if (rs.first()) {
			rs.close();
			return true;
		}
		else{
			rs.close();
			return false;
		}
	}
	
	public List<User> getAllFriendsTest(User currentUser) throws SQLException {
		List<User> allFriends = new ArrayList<>();
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM FRIENDS WHERE FRIENDS_CURRENT_USER_ID ='"+ currentUser.getUserId() +"'");
		
		while(rs.next()){
			User user = new User();
			user.setUserId(rs.getLong("FRIENDS_TARGET_USER_ID"));
			user = this.findByUserIdTest(user.getUserId());
			allFriends.add(user);
		}
		return allFriends;
	}
	
	public List<User> getAllBlockedTest(User currentUser) throws SQLException {
		List<User> allBlocked = new ArrayList<>();
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM BLOCKED WHERE BLOCKED_CURRENT_USER_ID ='"+ currentUser.getUserId() +"'");
		
		while(rs.next()){
			User user = new User();
			user.setUserId(rs.getLong("BLOCKED_TARGET_USER_ID"));
			user = this.findByUserIdTest(user.getUserId());
			allBlocked.add(user);
		}
		return allBlocked;
	}
}
