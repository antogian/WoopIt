package gr.teicm.icd.controllers;

import static org.junit.Assert.*;
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
import gr.teicm.icd.data.services.GeolocationService;
import gr.teicm.icd.data.services.MessageService;
import gr.teicm.icd.data.services.UserService;

import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;

@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/applicationContext.xml", 
		"file:src/main/webapp/WEB-INF/spring/Spring-security.xml", 
		"file:src/main/webapp/WEB-INF/spring/dispatcher-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
@Transactional
public class GeoLocationControllerTest extends GeoLocationController {

	protected MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
    
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private GeolocationService geolocationService;
	
   @Before
    public void setup() {
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(context)
        		.addFilters(springSecurityFilterChain)
                .build();
	}
	
	@Test
	public void shouldReturnTrackmePath()
	{
		String path = "trackme";
		Assert.assertTrue(goTrackMe().equals(path));
	}

	@Test
	@WithMockUser("panos21")
	@Transactional
	@Rollback(true)
	public void testInboxSend() throws Exception {
		
		mockMvc.perform(post("/trackme")
				.with(testSecurityContext())
				.param("userLatitude", "37.9120426000")
				.param("userLongitude", "23.7098513000")
				.param("userRadius", "100"))
				.andExpect(status().is3xxRedirection()); 

        User user = new User();
        user = geolocationService.getGeoLocation("panos21");
        
        assertEquals(user.getUserLatitude(), 37.9120426000, 0.0001);
        assertEquals(user.getUserLongitude(), 23.7098513000, 0.0001);
        assertEquals(user.getUserRadius(), 100, 0);
	}
	
	@Test
	@WithMockUser("panos21")
	@Transactional
	@Rollback(true)
	public void testInboxSendWithZeroCoords() throws Exception {
		
		mockMvc.perform(post("/trackme")
				.with(testSecurityContext())
				.param("userLatitude", "0")
				.param("userLongitude", "0")
				.param("userRadius", "100"))
				.andExpect(status().is3xxRedirection()); 

        User user = new User();
        user = geolocationService.getGeoLocation("panos21");
        
        assertEquals(user.getUserLatitude(), 0, 0.0001);
        assertEquals(user.getUserLongitude(), 0, 0.0001);
        assertEquals(user.getUserRadius(), 100, 0);
	}
	
	@Test
	@WithMockUser("panos21")
	@Transactional
	@Rollback(true)
	public void testInboxSendWithLongCoords() throws Exception {
		
		mockMvc.perform(post("/trackme")
				.with(testSecurityContext())
				.param("userLatitude", "33.1234567890123456789")
				.param("userLongitude", "32.1234567890123456789")
				.param("userRadius", "100"))
				.andExpect(status().is3xxRedirection()); 

        User user = new User();
        user = geolocationService.getGeoLocation("panos21");
        
        assertEquals(user.getUserLatitude(), 33.1234567, 0.0001);
        assertEquals(user.getUserLongitude(), 32.1234567, 0.0001);
        assertEquals(user.getUserRadius(), 100, 0);
	}
	
	@Test
	@WithMockUser("panos21")
	@Transactional
	@Rollback(true)
	public void testInboxSendWithWrongCoords() throws Exception {

		mockMvc.perform(post("/trackme")
				.with(testSecurityContext())
				.param("userLatitude", "abc")
				.param("userLongitude", "ddd")
				.param("userRadius", "100"))
				.andExpect(status().is4xxClientError()); 
	}
	
	@Test
	@WithMockUser("panos21")
	@Transactional
	@Rollback(true)
	public void testInboxSendWithBiggerRadius() throws Exception {
		
		mockMvc.perform(post("/trackme")
				.with(testSecurityContext())
				.param("userLatitude", "37.9120426000")
				.param("userLongitude", "23.7098513000")
				.param("userRadius", "200"))
				.andExpect(status().is3xxRedirection()); 

        User user = new User();
        user = geolocationService.getGeoLocation("panos21");
        
        assertEquals(user.getUserLatitude(), 37.9120426000, 0.0001);
        assertEquals(user.getUserLongitude(), 23.7098513000, 0.0001);
        assertEquals(user.getUserRadius(), 100, 0);
	}
}