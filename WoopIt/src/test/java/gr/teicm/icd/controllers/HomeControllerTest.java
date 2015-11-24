package gr.teicm.icd.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

public class HomeControllerTest {
	private HomeController homeController;

	@Before
	public void setUp() throws Exception {
		homeController = new HomeController();
	}

	@After
	public void tearDown() throws Exception {
		homeController = null;
	}

	@Test
	public void ShouldReturnIndexPath() {
		String expectedPath = "welcome";
		String receivedPath = homeController.goIndex();
		assertEquals(expectedPath, receivedPath);
		
	}
	
	@Test
	public void ShouldReturnWelcomePath() {
		String expectedPath = "welcome";
		String receivedPath = homeController.goWelcome();
		assertEquals(expectedPath, receivedPath);
	}
	
	@Test
	public void ShouldReturnHowToPath() {
		String expectedPath = "howto";
		String receivedPath = homeController.goHowto();
		assertEquals(expectedPath, receivedPath);
		
	}
	
	@Test
	public void ShouldReturnSupportPath() {
		String expectedPath = "support";
		String receivedPath = homeController.goSupport();
		
		assertEquals(expectedPath, receivedPath);
		
	}

}
