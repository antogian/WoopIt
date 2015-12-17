package gr.teicm.icd.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
public class InboxSendTest {
	
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
	@Transactional
	@Rollback(true)
	public void testInboxSend() throws Exception {

        mockMvc.perform(post("/user/inboxSend")
	        .with(testSecurityContext())
	        .param("receiverUser", "panos21")
	        .param("body", "TEST"))
        	.andExpect(status().is3xxRedirection())
        	.andExpect(flash().attribute("userName", "panos21"));

	}
	
	@Test
	@WithMockUser("panos21")
	@Transactional
	@Rollback(true)
	public void testInboxSendToUnkownUser() throws Exception {

        mockMvc.perform(post("/user/inboxSend")
	        .with(testSecurityContext())
	        .param("receiverUser", "unkownUser")
	        .param("body", "TEST UNKOWN USER"))
        	.andExpect(status().is3xxRedirection())
        	.andExpect(flash().attribute("userName", "unkownUser"));
	}
	
	@Test
	@WithMockUser("panos21")
	@Transactional
	@Rollback(true)
	public void testInboxSendWithEmptyBody() throws Exception {

        mockMvc.perform(post("/user/inboxSend")
	        .with(testSecurityContext())
	        .param("receiverUser", "panos21")
	        .param("body", ""))
        	.andExpect(status().is3xxRedirection())
        	.andExpect(flash().attribute("userName", "panos21"));
	}
}
