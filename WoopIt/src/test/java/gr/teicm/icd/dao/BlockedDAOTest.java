package gr.teicm.icd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gr.teicm.icd.data.entities.User;

public class BlockedDAOTest {
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/testwoopit", System.getenv("USER"), System.getenv("PASS"));
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
	
	public List<User> getAllBlockedTest(User currentUser) throws SQLException {
		List<User> allBlocked = new ArrayList<>();
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM BLOCKED WHERE BLOCKED_CURRENT_USER_ID ='"+ currentUser.getUserId() +"'");
		
		while(rs.next()){
			UserDAOTest userDAOTest = new UserDAOTest();
			User user = new User();
			user = userDAOTest.findByUserIdTest(rs.getLong("BLOCKED_TARGET_USER_ID"));
			allBlocked.add(user);
		}
		return allBlocked;
	}
}
