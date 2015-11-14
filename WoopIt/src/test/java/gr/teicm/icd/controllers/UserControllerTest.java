package gr.teicm.icd.controllers;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

public class UserControllerTest extends UserController {

	@Test
	public void shouldReturnRegisterPath()
	{
		//Given
		String path = "register";
		//Then
		Assert.assertTrue(createUser().equals(path));
	}
/*
	@Test
	public void shouldReturnRegisterPOSTPath()
	{
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		//Given
		String path = "register";
		User user = new User();
		user.setUserName("testUser");
		user.setUserPass("userPass");
		user.setUserEmail("user@mail.com");
		user.setUserSex("Male");
		user.setUserCountry("Africa");
		//Then
		Assert.assertTrue(createUserPOST(user, redirectAttributes).equals(path));
	}
*/
	@Test
	public void shouldReturnLoginPath()
	{
		//Given
		String path = "login";
		//Then
		Assert.assertTrue(loginPage().equals(path));
	}
	
	
	@Test
	public void shouldReturnLogoutPath()
	{
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);   
        
		//Given
        String path = "redirect:/user/login?logout";
        //Then
        Assert.assertTrue(logoutPage(request, response).equals(path));
	}
	
}
