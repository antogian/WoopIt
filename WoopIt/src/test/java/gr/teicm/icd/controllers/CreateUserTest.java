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
public class CreateUserTest {
	
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
	public void testUserSignUpWithAnAlreadyExistedUsernameShouldExpectRegisterFailed() throws Exception {

        mockMvc.perform(post("/user/register")
	        .with(testSecurityContext())
	        .param("userName", "panos21")
	        .param("userPass", "testPass")
	        .param("userEmail", "asd123333@asd123333.com"))
        	.andExpect(status().is3xxRedirection())
        	.andExpect(redirectedUrl("registerFailed"))
        	.andExpect(flash().attribute("userName", "panos21"));
	}
	
	@Test
	public void testUserSignUpWithAnAlreadyExistedEmailShouldExpectRegisterFailed() throws Exception {

        mockMvc.perform(post("/user/register")
	        .with(testSecurityContext())
	        .param("userName", "panos32123")
	        .param("userPass", "testPass")
	        .param("userEmail", "asd@asd.com"))
        	.andExpect(status().is3xxRedirection())
        	.andExpect(redirectedUrl("registerFailed"))
        	.andExpect(flash().attribute("userName", "panos32123"));
	}
}
