package gr.teicm.icd.controllers;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import javax.servlet.Filter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
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
public class UserLogoutTest {
	
	protected MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
    
	@Autowired
	private Filter springSecurityFilterChain;
	
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
	        		.webAppContextSetup(context)
	        		.addFilters(springSecurityFilterChain)
	                .build();
    }
	
	@Test
	@WithMockUser("panos21")
	public void testUserLogoutWhenUserPanos21IsLoggedInShouldExpectNullAuthentication() throws Exception {

        mockMvc.perform(get("/user/logout")
	        .with(testSecurityContext()))
        	.andExpect(status().is3xxRedirection())
        	.andExpect(redirectedUrl("/user/login?logout"));
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assertNull(auth);
	}

	@Test
	public void testUserLogoutWhenUserIsAlreadyLoggedOutShouldExpectNullAuthentication() throws Exception {

        mockMvc.perform(get("/user/logout")
	        .with(testSecurityContext()))
        	.andExpect(status().is3xxRedirection())
        	.andExpect(redirectedUrl("/user/login?logout"));
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assertNull(auth);
	}
}
