package gr.teicm.icd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
