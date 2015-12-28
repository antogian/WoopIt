package gr.teicm.icd.dao;

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

public class MessageDAOTest {

	protected MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
    
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService UserService;
	
    @Before
    public void setup() 
    {
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(context)
        		.addFilters(springSecurityFilterChain)
                .build();
    }
	
    @Test
    @WithMockUser("test")
	public void testIfMessageBodyExistsAfterInsertion()
	{
		Message msg = new Message();
		User usr = new User();
		
		usr = this.UserService.getUserByName(this.UserService.getLoggedInUsername());
		msg.setSender(usr);
		msg.setBody("Sample Message for Testing Purposes");
		msg.setDuration(30);
		msg.setMessageLongitude(usr.getUserLongitude());
		msg.setMessageLatitude(usr.getUserLatitude());
		msg.setMessageRadius(usr.getUserRadius());
		
		this.messageService.insertMessage(msg);
		
		List<Message> allMessages = new ArrayList<>();
		allMessages = this.messageService.getAllMessages();
		
		Integer i = 0;
		Boolean doesMessageBodyExist = false;
		while(i < allMessages.size())
		{
			if(allMessages.get(i).getBody().equals(msg.getBody()))
			{
				doesMessageBodyExist = true;
			}
			i++;
		}
		
		Assert.assertTrue(doesMessageBodyExist);
	}
    
    @Test
    @WithMockUser("test")
	public void testIfMessageBodyExistsAfterDeletion()
	{
		Message msg = new Message();
		User usr = new User();
		
		usr = this.UserService.getUserByName(this.UserService.getLoggedInUsername());
		msg.setSender(usr);
		msg.setBody("Sample Message for Testing Purposes");
		msg.setDuration(600);
		msg.setMessageLongitude(usr.getUserLongitude());
		msg.setMessageLatitude(usr.getUserLatitude());
		msg.setMessageRadius(usr.getUserRadius());
		Integer totalMessages = this.messageService.getAllMessages().size();
		
		this.messageService.insertMessage(msg);
		this.messageService.deleteMessage(this.messageService.getAllMessages().get(0));

		Assert.assertTrue(totalMessages == this.messageService.getAllMessages().size());
	}
	
}

