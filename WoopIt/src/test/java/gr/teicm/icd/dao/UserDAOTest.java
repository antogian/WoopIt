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
				"jdbc:mysql://83.212.116.34:3306/woopit", "#{systemEnvironment['WoopItUser']}", "#{systemEnvironment['WoopItPass']}");
	}
	
	public User getUser(double userId) throws SQLException {
		User user = new User();
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select * from USERS where USER_ID='"+ userId + "'");
		while (rs.next()) {
			user.setUserId(rs.getLong("USER_ID"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setUserLatitude(rs.getDouble("USER_LATITUDE"));
			user.setUserLongitude(rs.getDouble("USER_LONGITUDE"));
			user.setUserRadius(rs.getInt("USER_RADIUS"));
		}
		return user;
	}
}
