package services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.UserService;
import junit.framework.Assert;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;

import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.services.*;

import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import static org.hamcrest.Matchers.*;

import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.services.*;

import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;

@ContextConfiguration(locations = {
		"classpath:applicationContext.xml", 
		"classpath:Spring-security.xml", 
		"classpath:dispatcher-servlet.xml",
		"classpath:Spring-database.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testInsertPhotoPathShouldGetTheSameGivenPath() {
		User user = new User();
		user.setUserName("test");
		user.setUserPhotoPath("default.png");
		
		userService.insertPhotoPath(user);
		
		assertEquals(userService.getPhotoPath("test"), "default.png");
	}
	
	@Test
	public void testGetPhotoPathShouldGetDefaultPhotoPath() {
		assertEquals(userService.getPhotoPath("test"), "default.png");
	}

	@Test
	public void testGetPhotoPathOfANotExistedUserShouldExpectNullPath() {
		assertEquals(userService.getPhotoPath("testxxx"), null);
	}
	
	@Test
	public void testRemovePhotoPathOfUserTestShouldExpectDefaultPhotoPath(){
		User user = new User();
		user.setUserName("test");
		//change photo path to something else other than the default (default.png)
		user.setUserPhotoPath("notDefault.png");
		userService.insertPhotoPath(user);
		//remove photo
		userService.removePhoto(user);
		//the photo is removed and should return the default path (default.png)
		assertEquals(userService.getPhotoPath("test"), "default.png");
	}
	
	@Test
	public void testGetUserByIdShouldReturnTheCorrectUser() {
		User user = new User();
		// ID: 338, is user panos21
		user = userService.getUserById(338L);
		assertEquals(user.getUserName(), "panos21");
	}
	
	@Test
	public void testGetUserByIdWithWrongGivenIdShouldReturnEmptyUser() {
		User user = new User();
		user = userService.getUserById(4444L);
		assertEquals(user.getUserName(), null);
	}
}
