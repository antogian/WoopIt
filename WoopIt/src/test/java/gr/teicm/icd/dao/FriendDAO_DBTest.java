package gr.teicm.icd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import gr.teicm.icd.data.entities.User;

public class FriendDAO_DBTest extends DatabaseTestCase{
	private FriendDAOTest friendDaoTest;
	private Connection jdbcConnection;
	
	protected IDatabaseConnection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/testwoopit", System.getenv("USER"), System.getenv("PASS"));
		return new DatabaseConnection(jdbcConnection);
	}
	
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader()
				.getResourceAsStream("dbunitData.xml"));
	}
	
	@Test
	public void testAddUserToFriends(User currentUser){
		friendDaoTest = new FriendDAOTest();
	}
	
	@Test
	public void testIsFriend() throws SQLException {
		friendDaoTest = new FriendDAOTest();
		Boolean isFriend;
		User currentUser = new User(); 
		User targetUser = new User();
		
		currentUser.setUserId(1L);
		targetUser.setUserId(2L);
		
		isFriend = friendDaoTest.isFriendTest(currentUser, targetUser);
		assertTrue(isFriend);
	}
	
	@Test
	public void testGetAllFriends() throws SQLException {
		friendDaoTest = new FriendDAOTest();
		User currentUser = new User();
		currentUser.setUserId(1L);
		List<User> allFriends = new ArrayList<>();
		
		allFriends = friendDaoTest.getAllFriendsTest(currentUser);
		
		assertNotNull(allFriends);
	}
}
