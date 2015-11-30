package gr.teicm.icd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import gr.teicm.icd.data.entities.Message;
import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.dao.UserDAO;
@Repository
public class UserDAOImpl implements UserDAO {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(User user){
		
		String sql = "INSERT INTO USERS " +
				"(USER_NAME, USER_PASS, USER_EMAIL, USER_SEX, USER_COUNTRY, USER_PHOTO) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPass());
			ps.setString(3, user.getUserEmail());
			ps.setString(4, user.getUserSex());
			ps.setString(5, user.getUserCountry());
			ps.setString(6, user.getUserPhotoPath());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public void insertRole(User user){
		
		String sql = "INSERT INTO USER_ROLES " +
				"(USER_NAME, USER_ROLE) VALUES (?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, "ROLE_USER");
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public User findByUserId(Long userId){
		
		String sql = "SELECT * FROM USERS WHERE USER_ID = ?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, userId);
			User user = new User();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setUserId(rs.getLong("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserPass(rs.getString("USER_PASS"));
				user.setUserEmail(rs.getString("USER_EMAIL"));
				user.setUserSex(rs.getString("USER_SEX"));
				user.setUserCountry(rs.getString("USER_COUNTRY"));
				user.setUserPhotoPath(rs.getString("USER_PHOTO"));
			}
			rs.close();
			ps.close();
			return user;
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
	
	public User getUserByName(String userName){
	String sqlQuery = "SELECT * FROM USERS WHERE USER_NAME = ?";
	Connection conn = null;
	
	try{
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(sqlQuery);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		User user = new User();
		if(rs.next()){
			user.setUserId(rs.getLong("USER_ID"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setUserPass(rs.getString("USER_PASS"));
			user.setUserEmail(rs.getString("USER_EMAIL"));
			user.setUserSex(rs.getString("USER_SEX"));
			user.setUserCountry(rs.getString("USER_COUNTRY"));
			user.setUserPhotoPath(rs.getString("USER_PHOTO"));
			user.setUserLatitude(rs.getDouble("USER_LATITUDE"));
			user.setUserLongitude(rs.getDouble("USER_LONGITUDE"));
			user.setUserRadius(rs.getInt("USER_RADIUS"));
		}
		ps.close();
		rs.close();
		return user;
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
	
	public Boolean checkIfUserNameExist(String userName){
		
		String sql = "SELECT * FROM USERS WHERE USER_NAME = ?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
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
	
	public void insertPhotoPath(User user){
		String sql = "UPDATE USERS " + "SET USER_PHOTO = ? " + "WHERE USER_NAME = ?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserPhotoPath());
			ps.setString(2, user.getUserName());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public String getPhotoPath(String userName){
		
		String sql = "SELECT USER_PHOTO FROM USERS WHERE USER_NAME = ?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			String photoPath = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				photoPath = rs.getString("USER_PHOTO");
			}
			rs.close();
			ps.close();
			return photoPath;
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
	
    public void removePhoto(User user){
		String sql = "UPDATE USERS " + "SET USER_PHOTO = ? " + "WHERE USER_NAME = ?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "default.png");
			ps.setString(2, user.getUserName());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
    }
    
    public void insertGeoLocation(User user){
    	String sql = "UPDATE USERS " + "SET USER_LATITUDE = ?, USER_LONGITUDE = ?, USER_RADIUS = ? " + "WHERE USER_NAME = ?";
    	Connection conn = null;
    	
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, user.getUserLatitude());
			ps.setDouble(2, user.getUserLongitude());
			ps.setInt(3, user.getUserRadius());
			ps.setString(4, user.getUserName());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
    }
    
	public User getGeoLocation(String userName){
		
		String sql = "SELECT USER_LATITUDE, USER_LONGITUDE, USER_RADIUS FROM USERS WHERE USER_NAME = ?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			User user = new User();
			if(rs.next()){
				user.setUserLatitude(rs.getDouble("USER_LATITUDE"));
				user.setUserLongitude(rs.getDouble("USER_LONGITUDE"));
				user.setUserRadius(rs.getInt("USER_RADIUS"));
			}
			ps.close();
			rs.close();
			return user;
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
    
    public void addUserToBlocked(User currentUser, User targetUser){
		String sqlQuery = "INSERT INTO BLOCKED " +
				"(BLOCKED_CURRENT_USER_ID, BLOCKED_TARGET_USER_ID) VALUES (?, ?)";
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
    
    public void removeUserFromBlocked(User currentUser, User targetUser){
		String sqlQuery = "DELETE FROM BLOCKED WHERE BLOCKED_CURRENT_USER_ID=? AND BLOCKED_TARGET_USER_ID=?";
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
	
	public Boolean isBlocked(User currentUser, User targetUser){
		
		String sqlQuery = "SELECT * FROM BLOCKED WHERE BLOCKED_CURRENT_USER_ID=? AND BLOCKED_TARGET_USER_ID=?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
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
		} 
		catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		finally {
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
				user = this.findByUserId(user.getUserId());
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
	
	public List<User> getAllBlocked(User currentUser){
		List<User> allBlocked = new ArrayList<>();
		String sqlQuery = "SELECT * FROM BLOCKED WHERE BLOCKED_CURRENT_USER_ID=?";
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
				user.setUserId(rs.getLong("BLOCKED_TARGET_USER_ID"));
				user = this.findByUserId(user.getUserId());
				allBlocked.add(user);
			}
			ps.close();
			return allBlocked;
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
	
	public User getUserById (Long userId){
		String sqlQuery = "SELECT * FROM USERS WHERE USER_ID = ?";
		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();
			User user = new User();
			if(rs.next()){
				user.setUserId(rs.getLong("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserPass(rs.getString("USER_PASS"));
				user.setUserEmail(rs.getString("USER_EMAIL"));
				user.setUserSex(rs.getString("USER_SEX"));
				user.setUserCountry(rs.getString("USER_COUNTRY"));
				user.setUserPhotoPath(rs.getString("USER_PHOTO"));
				user.setUserLatitude(rs.getDouble("USER_LATITUDE"));
				user.setUserLongitude(rs.getDouble("USER_LONGITUDE"));
				user.setUserRadius(rs.getInt("USER_RADIUS"));
			}
			ps.close();
			rs.close();
			return user;
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
	
	public boolean userExists(String keyword){
		String sqlQuery = "SELECT * FROM USERS WHERE USER_NAME LIKE ?";
		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			keyword = "%" + keyword + "%";
			ps.setString(1, keyword);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				ps.close();
				rs.close();
		
				return true;
			}
			else{
				ps.close();
				rs.close();
				
				return false;
			}
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
	
	public List<User> searchUsersByKeyword(String keyword){
		String sqlQuery = "SELECT * FROM USERS WHERE USER_NAME LIKE ?";
		Connection conn = null;
		List<User> matchedUsers = new ArrayList<>();
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			keyword = "%" + keyword + "%";
			ps.setString(1, keyword);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				User user = new User();
				user = this.getUserByName(rs.getString("USER_NAME"));
				matchedUsers.add(user);
			}
			ps.close();
			rs.close();
			
			return matchedUsers;
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
