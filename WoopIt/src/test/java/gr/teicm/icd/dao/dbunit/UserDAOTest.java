package gr.teicm.icd.dao.dbunit;

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
				"jdbc:mysql://127.0.0.1:3306/testwoopit", System.getenv("USER"), System.getenv("PASS"));
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
	
}
