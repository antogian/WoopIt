package gr.teicm.icd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import gr.teicm.icd.dao.*;
import gr.teicm.icd.data.entities.User;

public class FriendDAOImpl implements FriendDAO{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
    public void addUserToFriends(User currentUser, User targetUser){
		String sqlQuery = "INSERT INTO FRIENDS " +
				"(FRIENDS_CURRENT_USER_ID, FRIENDS_TARGET_USER_ID) VALUES (?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, Long.toString(currentUser.getUserId()));
			ps.setString(2, Long.toString(targetUser.getUserId()));
			ps.executeUpdate();
			ps.close();
			
		} 
		catch (SQLException e) {
			throw new RuntimeException(e);
			
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e) 
				{}
			}
		}
    }
    
    public void removeUserFromFriends(User currentUser, User targetUser){
		String sqlQuery = "DELETE FROM FRIENDS WHERE FRIENDS_CURRENT_USER_ID=? AND FRIENDS_TARGET_USER_ID=?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, Long.toString(currentUser.getUserId()));
			ps.setString(2, Long.toString(targetUser.getUserId()));
			ps.executeUpdate();
			ps.close();
			
		} 
		catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e) 
				{}
			}
		}
    }
    
	public Boolean isFriend(User currentUser, User targetUser){
		
		String sql = "SELECT * FROM FRIENDS WHERE FRIENDS_CURRENT_USER_ID=? AND FRIENDS_TARGET_USER_ID=?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Long.toString(currentUser.getUserId()));
			ps.setString(2, Long.toString(targetUser.getUserId()));
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
	
	public List<User> getAllFriends(User currentUser){
		List<User> allFriends = new ArrayList<>();
		String sqlQuery = "SELECT * FROM FRIENDS WHERE FRIENDS_CURRENT_USER_ID=?";
		Connection conn = null;
		UserDAOImpl userDao = new UserDAOImpl();
		userDao.setDataSource(this.dataSource);
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, Long.toString(currentUser.getUserId()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getLong("FRIENDS_TARGET_USER_ID"));
				user = userDao.findByUserId(user.getUserId());
				allFriends.add(user);
			}
			ps.close();
			return allFriends;
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
