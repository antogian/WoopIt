package gr.teicm.icd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import gr.teicm.icd.data.entities.User;

public class UserDAO_DBTest extends DatabaseTestCase {
	private UserDAOTest userDaoTest;
	private Connection jdbcConnection;
	
	protected IDatabaseConnection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://83.212.116.34:3306/testwoopit", System.getenv("WoopItUser"), System.getenv("WoopItPass"));
		return new DatabaseConnection(jdbcConnection);
	}
	
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader()
				.getResourceAsStream("dbunitData.xml"));
	}
	
	@Test
	public void testGetUser() throws SQLException {
		userDaoTest = new UserDAOTest();
		User user = new User();
		user = userDaoTest.findByUserIdTest(1L);
		assertEquals("TestUser", user.getUserName());
	}
	
	@Test
	public void testGetUserByName() throws SQLException {
		userDaoTest = new UserDAOTest();
		User user = new User();
		user = userDaoTest.getUserByNameTest("TestUser");
		assertEquals("TestUser", user.getUserName());
	}
	
	@Test
	public void testCheckIfUserNameExistTest() throws SQLException {
		userDaoTest = new UserDAOTest();
		Boolean isExist;
		isExist = userDaoTest.checkIfUserNameExistTest("TestUser");
		assertTrue(isExist);
	}
}
