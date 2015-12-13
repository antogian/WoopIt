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

public class BlockedDAO_DBTest extends DatabaseTestCase{
	private BlockedDAOTest blockedDaoTest;
	private Connection jdbcConnection;
	
	protected IDatabaseConnection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/testwoopit", System.getProperty("USER"), System.getProperty("PASS"));
		return new DatabaseConnection(jdbcConnection);
	}
	
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader()
				.getResourceAsStream("dbunitData.xml"));
	}
	
	@Test
	public void testIsBlocked() throws SQLException {
		blockedDaoTest = new BlockedDAOTest();
		Boolean isBlocked;
		User currentUser = new User(); 
		User targetUser = new User();
		
		currentUser.setUserId(1L);
		targetUser.setUserId(3L);
		
		isBlocked = blockedDaoTest.isBlockedTest(currentUser, targetUser);
		assertTrue(isBlocked);
	}
	
	@Test
	public void testGetAllBlocked() throws SQLException {
		blockedDaoTest = new BlockedDAOTest();
		User currentUser = new User();
		currentUser.setUserId(1L);
		List<User> allBlocked = new ArrayList<>();
		
		allBlocked = blockedDaoTest.getAllBlockedTest(currentUser);
		
		assertNotNull(allBlocked);
	}
}
