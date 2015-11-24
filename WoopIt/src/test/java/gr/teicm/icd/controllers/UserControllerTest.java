package gr.teicm.icd.controllers;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.UserService;

public class UserControllerTest extends UserController {
	
	@Test
	public void shouldReturnRegisterPath()
	{
		String path = "register";
		Assert.assertTrue(createUser().equals(path));
	}

	@Test
	public void shouldReturnLoginPath()
	{
		String path = "login";
		Assert.assertTrue(loginPage().equals(path));
	}
	
	@Test
	public void shouldReturnLogoutPath()
	{
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);   
        
        String path = "redirect:/user/login?logout";
        Assert.assertTrue(logoutPage(request, response).equals(path));
	}
	
	@Test
	public void shouldReturnRegisterSuccessPath()
	{
		HttpServletRequest request = mock(HttpServletRequest.class); 
		String path = "registerSuccess";
		Assert.assertTrue(successView(request).equals(path));
	}
	
	@Test
	public void shouldReturnRegisterFailedPath()
	{
		HttpServletRequest request = mock(HttpServletRequest.class); 
		String path = "registerFailed";
		Assert.assertTrue(failedView(request).equals(path));
	}
	
	public void shouldReturnViewProfilePath()
	{
		String path = "viewprofile";
		Assert.assertTrue(("viewprofile").equals(path));
	}
	
	@Test
	public void shouldReturnEditProfilePath()
	{
		String path = "editprofile";
		Assert.assertTrue(editProfileView().equals(path));
	}
	
	@Test
	public void shouldReturnHistoryPath()
	{
		String path = "history";
		Assert.assertTrue(historyView().equals(path));
	}
	
	@Test
	public void shouldReturnSettingsPath()
	{
		String path = "settings";
		Assert.assertTrue(settingsView().equals(path));
	}
	
	@Test
	public void shouldReturnInboxPath()
	{
		String path = "inbox";
		Assert.assertTrue(inboxView().equals(path));
	}
}
