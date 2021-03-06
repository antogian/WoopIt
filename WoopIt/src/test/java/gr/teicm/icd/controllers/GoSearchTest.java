package gr.teicm.icd.controllers;

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
@Transactional
public class GoSearchTest {
	
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
    public void setup() 
    {
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(context).addFilters(springSecurityFilterChain)
                .build();
    }
	
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testIfModelAttributeExistsI() throws Exception
    {
    	String key = "t";
        mockMvc.perform(post("/searchResults").with(testSecurityContext())
        								.param("keyword", key))
										.andExpect(status().isOk())
										.andExpect(model().attributeExists("matchedUsers"))
										.andExpect(model().attributeExists("keyword"));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testIfModelAttributeExistsII() throws Exception
    {
    	String key = "#!#!";
        mockMvc.perform(post("/searchResults").with(testSecurityContext())
        								.param("keyword", key))
										.andExpect(status().isOk())
										.andExpect(model().attributeDoesNotExist("matchedUsers"))
										.andExpect(model().attributeExists("keyword"));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testModelAttributeSize() throws Exception
    {
    	String key = "t";
        List<User> matchedUsers = this.userService.searchUsersByKeyword(key);
    	
    	mockMvc.perform(post("/searchResults").with(testSecurityContext())
        								.param("keyword", key))
										.andExpect(status().isOk())
										.andExpect(model().attribute("matchedUsers", hasSize(matchedUsers.size())));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testIfModelAttributeIsNull() throws Exception
    {
    	String key = "t";
        mockMvc.perform(post("/searchResults").with(testSecurityContext())
        								.param("keyword", key))
										.andExpect(status().isOk())
										.andExpect(model().attribute("matchedUsers", notNullValue()))
										.andExpect(model().attribute("keyword", notNullValue()));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testModelAttributeType() throws Exception
    {
    	String key = "t";
        mockMvc.perform(post("/searchResults").with(testSecurityContext())
        								.param("keyword", key))
										.andExpect(status().isOk())
										.andExpect(model().attribute("matchedUsers", isA(ArrayList.class)))
										.andExpect(model().attribute("keyword", isA(String.class)));
    }
    
    @Test//(timeout=10000)
    @WithMockUser("test")
    public void testModelAttributeValues() throws Exception
    {
    	String key = "t";
        mockMvc.perform(post("/searchResults").with(testSecurityContext())
        								.param("keyword", key))
										.andExpect(status().isOk())
										.andExpect(model().attribute("matchedUsers", hasItem(allOf(hasProperty("userName", is("test"))))))
										.andExpect(model().attribute("keyword", is(key)));
    }
}
