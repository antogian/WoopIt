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

@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring/Spring-security.xml", "file:src/main/webapp/WEB-INF/spring/dispatcher-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
//@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
//@Transactional

public class DisplayAllMessagesTest {
	
	protected MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
    
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private BlockedService blockedService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
    @Before
    public void setup() 
    {
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(context).addFilters(springSecurityFilterChain)
                .build();
    }
    
    private List<Message> removeBlockedMessages(List<Message> allMessages, User currentUser)
    {
    	int i = 0;
    	
    	while(i < allMessages.size()){
        	if(this.blockedService.isBlocked(currentUser, allMessages.get(i).getSender())){
        		allMessages.remove(i);
    		}
        	i++;
    	}
    	
    	return allMessages;
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testIfModelAttributeExists() throws Exception
    {
        mockMvc.perform(get("/home").with(testSecurityContext()))
											.andExpect(status().isOk())
											.andExpect(model().attributeExists("allMessages"));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testModelAttributeType() throws Exception
    {
        mockMvc.perform(get("/home").with(testSecurityContext()))
											.andExpect(status().isOk())
											.andExpect(model().attribute("allMessages", isA(ArrayList.class)));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testModelAttributeSize() throws Exception
    {
    	List<Message> allMessages = new ArrayList<>();
    	User currentUser = this.userService.getUserByName("test");
    	allMessages = this.messageService.getAllMessages();
    	allMessages = this.removeBlockedMessages(allMessages, currentUser);
    	
        mockMvc.perform(get("/home").with(testSecurityContext()))
											.andExpect(status().isOk())
											.andExpect(model().attribute("allMessages", hasSize(allMessages.size())));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testIfModelAttributeIsNull() throws Exception
    {
		mockMvc.perform(get("/home").with(testSecurityContext()))
											.andExpect(status().isOk())
											.andExpect(model()
											.attribute("allMessages", notNullValue()));
	}
    
    /*@SuppressWarnings("unchecked")
	@Test//(timeout=10000)
    @WithMockUser("test")
    public void testModelAttributeContent() throws Exception
    {
    	List<Message> allMessages = new ArrayList<>();
    	User currentUser = this.userService.getUserByName("test");
    	allMessages = this.messageService.getAllMessages();
    	allMessages = this.removeBlockedMessages(allMessages, currentUser);
    	
    	int i = 0;
    	while(i < allMessages.size()){
    		mockMvc.perform(get("/home").with(testSecurityContext()))
										.andExpect(status().isOk())
										.andExpect(model()
										.attribute("allMessages", hasItem(allOf(hasProperty("sender", hasItem(allOf(hasProperty("userName", hasValue(allMessages.get(i).getSender().getUserName())))))))));
    		i++;
    	}
	}*/
    
}
