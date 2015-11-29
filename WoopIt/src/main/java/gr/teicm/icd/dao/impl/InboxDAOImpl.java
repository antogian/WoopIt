package gr.teicm.icd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import gr.teicm.icd.dao.InboxDAO;
import gr.teicm.icd.dao.UserDAO;
import gr.teicm.icd.data.entities.Inbox;
import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.UserService;
@Repository
public class InboxDAOImpl implements InboxDAO {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insertInbox(Inbox pm) {
		String sqlQuery = "INSERT INTO INBOX " + 
				"(INBOX_RECEIVER_ID, INBOX_SENDER_ID, INBOX_BODY, INBOX_DATETIME) " +
				"VALUES(?, ?, ?, NOW())";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			
			ps.setLong(1, pm.getReceiverUser().getUserId());
			ps.setLong(2, pm.getSenderUser().getUserId());
			ps.setString(3, pm.getBody());
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
	
	public List<Inbox> getInbox(User user){
		List<Inbox> allInbox = new ArrayList<>();
		String sqlQuery = "SELECT * FROM INBOX WHERE INBOX_RECEIVER_ID=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setLong(1, user.getUserId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Inbox pm = new Inbox();
				pm.setId(rs.getLong("INBOX_ID"));
				pm.setReceiverUser(user);
				pm.setSenderUser(getUserById(rs.getLong("INBOX_SENDER_ID")));
				pm.setBody(rs.getString("INBOX_BODY"));
				pm.setDate(rs.getString("INBOX_DATETIME"));
				allInbox.add(pm);
			}
			ps.close();
			return allInbox;
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
	
	public List<Inbox> getHistory(User user) {
		List<Inbox> allHistory = new ArrayList<>();
		String sqlQuery = "SELECT * FROM INBOX WHERE INBOX_SENDER_ID=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setLong(1, user.getUserId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Inbox pm = new Inbox();
				pm.setReceiverUser(getUserById(rs.getLong("INBOX_RECEIVER_ID")));
				pm.setSenderUser(user);
				pm.setBody(rs.getString("INBOX_BODY"));
				pm.setDate(rs.getString("INBOX_DATETIME"));
				allHistory.add(pm);
			}
			ps.close();
			return allHistory;
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
	
	public int getNewInboxQuantity(User user) {
		String sqlQuery = "select count(*) from INBOX WHERE INBOX_RECEIVER_ID=? AND INBOX_READ=0";
		Connection conn = null;
		UserDAOImpl userDAO = new UserDAOImpl();
		userDAO.setDataSource(this.dataSource);
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setLong(1, user.getUserId());
			ResultSet rs = ps.executeQuery();
			rs.next();
			int rowCount = rs.getInt(1);
			return rowCount;
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
	
	public void deleteInbox(Long id, User user) {
		String sqlQuery = "DELETE FROM INBOX WHERE INBOX_ID=? AND INBOX_RECEIVER_ID=?";
		Connection conn = null;
		UserDAOImpl userDAO = new UserDAOImpl();
		userDAO.setDataSource(this.dataSource);
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setLong(1, id);
			ps.setLong(2, user.getUserId());
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
	
	public void resetNewInboxQuantity(User user){
		String sqlQuery = "UPDATE INBOX SET INBOX_READ=1 WHERE INBOX_RECEIVER_ID=? AND INBOX_READ=0";
		Connection conn = null;
		UserDAOImpl userDAO = new UserDAOImpl();
		userDAO.setDataSource(this.dataSource);
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setLong(1, user.getUserId());
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
}
