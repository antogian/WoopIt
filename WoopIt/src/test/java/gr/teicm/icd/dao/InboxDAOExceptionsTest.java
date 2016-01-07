package gr.teicm.icd.dao;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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

import gr.teicm.icd.dao.impl.InboxDAOImpl;
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

public class InboxDAOExceptionsTest {

	protected MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
    
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private InboxDAOImpl inboxDAOImpl;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
    @Before
    public void setup() 
    {
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(context)
        		.addFilters(springSecurityFilterChain)
                .build();
    }
	
    @Test(expected = Exception.class)
    public void testGetInboxWithNonExistedUserShouldExpectException() {
    	User user = new User();
    	user.setUserName("null");
    	inboxDAOImpl.getInbox(user);
    	thrown.expect(SQLException.class);
    }
    
    @Test(expected = Exception.class)
    public void testInsertInboxWithNonExistedMessageShouldExpectException() {
    	Inbox pm = new Inbox();
    	User receiver = new User();
    	receiver.setUserId(null);
    	receiver.setUserName("null");
    	User sender = new User();
    	sender.setUserId(null);
    	sender.setUserName("null");
    	pm.setReceiverUser(receiver);
    	pm.setSenderUser(sender);
    	inboxDAOImpl.insertInbox(pm);
    	thrown.expect(SQLException.class);
    }
    
    @Test(expected = Exception.class)
    public void testGetHistoryWithNonExistedUserShouldExpectException() {
    	User user = new User();
    	user.setUserName("null");
    	inboxDAOImpl.getHistory(user);
    	thrown.expect(SQLException.class);
    }
    
    @Test(expected = Exception.class)
    public void testGetNewInboxQuantityWithNonExistedUserShouldExpectException() {
    	User user = new User();
    	user.setUserName("null");
    	inboxDAOImpl.getNewInboxQuantity(user);
    	thrown.expect(SQLException.class);
    }
    
    @Test(expected = Exception.class)
    public void testDeleteInboxWithNonExistedMessageShouldExpectException() {
    	User user = new User();
    	user.setUserName("null");
    	inboxDAOImpl.deleteInbox(null, user);
    	thrown.expect(SQLException.class);
    }
    
    @Test(expected = Exception.class)
    public void testResetNewInboxQuantityWithNonExistedMessageShouldExpectException() {
    	User user = new User();
    	user.setUserName("null");
    	inboxDAOImpl.resetNewInboxQuantity(user);
    	thrown.expect(SQLException.class);
    }
    
    
}

