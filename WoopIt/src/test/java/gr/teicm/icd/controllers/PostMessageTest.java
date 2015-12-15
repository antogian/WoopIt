package gr.teicm.icd.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.junit.Assert;
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
//@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
//@Transactional

public class PostMessageTest {
	
	protected MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
    
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private MessageService messageService;
	
    @Before
    public void setup() 
    {
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(context).addFilters(springSecurityFilterChain)
                .build();
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testIfModelAttributeExists() throws Exception
    {
    	String msg = "Sample Message";
        mockMvc.perform(post("/home").with(testSecurityContext())
        								.param("message", msg))
										.andExpect(status().isOk())
										.andExpect(model().attributeExists("allMessages"));
    }
    
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testModelAttributeType() throws Exception
    {
    	String msg = "Sample Message";
    	mockMvc.perform(post("/home").with(testSecurityContext())
        									.param("message", msg))
											.andExpect(status().isOk())
											.andExpect(model().attribute("allMessages", isA(ArrayList.class)));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testModelAttributeSize() throws Exception
    {
    	String msg = "Sample Message";
    	
    	List<Message> allMessages = new ArrayList<>();
    	allMessages = this.messageService.getAllMessages();
    	
        mockMvc.perform(post("/home").with(testSecurityContext())
        								.param("message", msg))
										.andExpect(status().isOk())
										.andExpect(model().attribute("allMessages", hasSize(allMessages.size() + 1)));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testIfModelAttributeIsNull() throws Exception
    {
    	String msg = "Sample Message";
    	mockMvc.perform(post("/home").with(testSecurityContext())
        									.param("message", msg))
											.andExpect(status().isOk())
											.andExpect(model().attribute("allMessages", notNullValue()));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testIfSampleMessageExists() throws Exception
	{
	    String msg = "Sample Message";
		mockMvc.perform(post("/home").with(testSecurityContext())
										.param("message", msg))
										.andExpect(status().isOk())
										.andExpect(model().attribute("allMessages", hasItem(allOf(hasProperty("body", is(msg))))));
	}

}
