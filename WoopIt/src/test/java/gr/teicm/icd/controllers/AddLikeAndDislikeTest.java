package gr.teicm.icd.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.Filter;

import org.junit.Assert;
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

import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.MessageService;
import gr.teicm.icd.data.services.UserService;

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
public class AddLikeAndDislikeTest {

	protected MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
    
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(context)
        		.addFilters(springSecurityFilterChain)
                .build();
    }

	@Test(timeout = 5000)
	@WithMockUser("panos21")
	public void testAddLike() throws Exception {
        mockMvc.perform(get("/like?msgId=13&like=true")
        		.with(testSecurityContext()))
        		.andExpect(status().is3xxRedirection());
        User user = new User();
        user = userService.getUserByName("panos21");
        Assert.assertTrue(messageService.checkIfLiked(user, 13L));
    }

	@Test(timeout = 5000)
	@WithMockUser("panos21")
	public void testAddDislikeWithMsgId() throws Exception {
        mockMvc.perform(get("/like?msgId=13&like=false")
        		.with(testSecurityContext()))
        		.andExpect(status().is3xxRedirection());
        User user = new User();
        user = userService.getUserByName("panos21");
        Assert.assertTrue(messageService.checkIfLiked(user, 13L));
    }

	@Test(timeout = 5000)
	@WithMockUser("panos21")
	public void testAddLikeWithNotExistedMsgId() throws Exception {
        mockMvc.perform(get("/like?msgId=133&like=true")
        		.with(testSecurityContext()))
        		.andExpect(status().is3xxRedirection());
        User user = new User();
        user = userService.getUserByName("panos21");
        Assert.assertTrue(messageService.checkIfLiked(user, 133L));
    }

	@Test(timeout = 5000)
	@WithMockUser("panos21")
	public void testAddLikeWithNotWrongMsgId() throws Exception {
        mockMvc.perform(get("/like?msgId=xxx&like=true")
        		.with(testSecurityContext()))
        		.andExpect(status().is4xxClientError());
    }
	
	@Test(timeout = 5000)
	@WithMockUser("panos21")
	public void testAddLikeWithWrongBoolean() throws Exception {
        mockMvc.perform(get("/like?msgId=15&like=xxx")
        		.with(testSecurityContext()))
        		.andExpect(status().is4xxClientError());
        User user = new User();
        user = userService.getUserByName("panos21");
        Assert.assertFalse(messageService.checkIfLiked(user, 15L));
    }
}
