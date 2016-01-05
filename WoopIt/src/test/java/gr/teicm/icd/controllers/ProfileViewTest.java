package gr.teicm.icd.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.awt.List;

import javax.servlet.Filter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import gr.teicm.icd.data.entities.User;

import static org.hamcrest.Matchers.*;
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
public class ProfileViewTest {
	
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
	public void testProfileViewOfTheSameUserShouldExpectAUserClassWithTargetUserWithTheSameName() throws Exception {
        mockMvc.perform(get("/viewprofile?name=panos21&friend=false&blocked=false")
	        .with(testSecurityContext()))
	        .andExpect(status().isOk())
	        .andExpect(model().attribute("targetUser", hasProperty("userName", is("panos21"))))
	        .andExpect(model().attribute("targetUser", isA(User.class))
        );
	}

	@Test
	@WithMockUser("panos21")
	public void testProfileViewOfAnotherUserShouldExpectAUserClassWithTheTargetUser() throws Exception {
        mockMvc.perform(get("/viewprofile?name=TestUser&friend=false&blocked=false")
	        .with(testSecurityContext()))
	        .andExpect(status().isOk())
	        .andExpect(model().attribute("targetUser", hasProperty("userName", is("TestUser"))))
	        .andExpect(model().attribute("targetUser", isA(User.class))
        );
	}
	
	@Test
	@WithMockUser("panos21")
	public void testProfileViewOfNotExistedUserShouldExpectAUserClassWithNullUserData() throws Exception {
        mockMvc.perform(get("/viewprofile?name=TestUser123&friend=false&blocked=false")
	        .with(testSecurityContext()))
	        .andExpect(status().isOk())
	        .andExpect(model().attribute("targetUser", hasProperty("userName",  is(nullValue()))))
	        .andExpect(model().attribute("targetUser", isA(User.class))
        );
	}
	
	@Test
	@WithMockUser("panos21")
	public void testProfileViewAnotherUserWithFriendParameterTrueShouldExpectAUserClassWithTheTargetUser() throws Exception {
        mockMvc.perform(get("/viewprofile?name=TestUser&friend=true&blocked=false")
	        .with(testSecurityContext()))
	        .andExpect(status().isOk())
	        .andExpect(model().attribute("targetUser", hasProperty("userName",  is("TestUser"))))
	        .andExpect(model().attribute("targetUser", isA(User.class))
        );
	}
	
	@Test
	@WithMockUser("panos21")
	public void testProfileViewAnotherUserWithFriendParameterFalseAndBlockedTrueShouldExpectAUserClassWithTheTargetUser() throws Exception {
        mockMvc.perform(get("/viewprofile?name=test&friend=false&blocked=true")
	        .with(testSecurityContext()))
	        .andExpect(status().isOk())
	        .andExpect(model().attribute("targetUser", hasProperty("userName",  is("test"))))
	        .andExpect(model().attribute("targetUser", isA(User.class))
        );
	}
}
