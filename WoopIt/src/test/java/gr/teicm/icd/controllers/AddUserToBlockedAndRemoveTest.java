package gr.teicm.icd.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import gr.teicm.icd.data.entities.*;
import gr.teicm.icd.data.services.*;
import junit.framework.Assert;

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

@Transactional
public class AddUserToBlockedAndRemoveTest
{
	protected MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
    
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private BlockedService blockedService;
	
	@Autowired
	private UserService userService;
	
    @Before
    public void setup() 
    {
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(context).addFilters(springSecurityFilterChain)
                .build();
    }
    
    @Test
	@WithMockUser("lalakis")
	public void testIfUserIsBlockedShouldReturnTrue() throws Exception 
	{
        mockMvc.perform(get("/viewprofile?name=test12345&friend=false&blocked=true")
        		.with(testSecurityContext()))
        		.andExpect(status().isOk());
        User lalakis = new User();
        User test12345 = new User();
        
        lalakis = userService.getUserByName("lalakis");
        test12345 = userService.getUserByName("test12345");
        
        assertTrue(blockedService.isBlocked(lalakis, test12345));
    }
    
    @Test
    @WithMockUser("lalakis")
	public void testIfUserIsRemovedFromBlockedShouldReturnFalse() throws Exception 
	{
        mockMvc.perform(get("/viewprofile?name=test12345&friend=true&blocked=false")
        		.with(testSecurityContext()))
        		.andExpect(status().isOk());
        User lalakis = new User();
        User test12345 = new User();
        
        lalakis = userService.getUserByName("lalakis");
        test12345 = userService.getUserByName("test12345");
        
        assertFalse(blockedService.isBlocked(lalakis, test12345));
    }
}
