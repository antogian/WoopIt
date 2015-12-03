package gr.teicm.icd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gr.teicm.icd.data.entities.User;

public class FriendDAOTest {
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://83.212.116.34:3306/testwoopit", System.getenv("WoopItUser"), System.getenv("WoopItPass"));
	}
	
    public void addUserToFriendsTest(User currentUser, User targetUser) throws SQLException{
    	Statement stmt = getConnection().createStatement();
    	stmt.executeQuery("INSERT INTO FRIENDS " + 
    			"(FRIENDS_CURRENT_USER_ID, FRIENDS_TARGET_USER_ID) VALUES (" + 
    			currentUser.getUserId() + ", " + targetUser.getUserId() +")" );
    	
    	stmt.close();
    }
    
    public void removeUserFromFriendsTest(User currentUser, User targetUser) throws SQLException{
    	Statement stmt = getConnection().createStatement();
    	stmt.executeQuery("DELETE FROM FRIENDS WHERE FRIENDS_CURRENT_USER_ID=" + currentUser.getUserId() + 
    									" AND FRIENDS_TARGET_USER_ID=" + targetUser.getUserId());
    	
    	stmt.close();
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
	
	public List<User> getAllFriendsTest(User currentUser) throws SQLException {
		List<User> allFriends = new ArrayList<>();
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM FRIENDS WHERE FRIENDS_CURRENT_USER_ID ='"+ currentUser.getUserId() +"'");
		
		while(rs.next()){
			UserDAOTest userDAOTest = new UserDAOTest();
			User user = new User();
			user = userDAOTest.findByUserIdTest(rs.getLong("FRIENDS_TARGET_USER_ID"));
			allFriends.add(user);
		}
		
		return allFriends;
	}
}
