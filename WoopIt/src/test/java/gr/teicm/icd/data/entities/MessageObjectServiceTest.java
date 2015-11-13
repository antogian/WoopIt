package gr.teicm.icd.data.entities;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class MessageObjectServiceTest {	

	@Test
	public void shouldCreateMessageAndReturnBody()
	{
		//Given
		String body = "This is a a message.";
		//When
		Message msg = new Message();
		msg.setBody(body);
		//Then
		Assert.assertTrue(msg.getBody().equals(body));
	}
	
	@Test
	public void shouldCreateMessageAndReturnBody2()
	{
		//Given
		String body = "Auto einai ena minima";
		//When
		Message msg = new Message();
		msg.setBody(body);
		//Then
		Assert.assertTrue(msg.getBody().equals(body));
	}
	
	@Test
	public void shouldCreateMessageAndReturnBody3()
	{
		//Given
		String body = "123321";
		//When
		Message msg = new Message();
		msg.setBody(body);
		//Then
		Assert.assertTrue(msg.getBody().equals(body));
	}
	
	@Test
	public void shouldCreateMessageAndReturnBody4()
	{
		//Given
		String body = "1!2@3#4$%5";
		//When
		Message msg = new Message();
		msg.setBody(body);
		//Then
		Assert.assertTrue(msg.getBody().equals(body));
	}
	
	@Test
	public void shouldCreateMessageAndReturnBody5()
	{
		//Given
		String body = "/*-+22";
		//When
		Message msg = new Message();
		msg.setBody(body);
		//Then
		Assert.assertTrue(msg.getBody().equals(body));
	}
	
	@Test
	public void shouldCreateMessageAndReturnBody6()
	{
		//Given
		String body = "21asadas21";
		//When
		Message msg = new Message();
		msg.setBody(body);
		//Then
		Assert.assertTrue(msg.getBody().equals(body));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderName()
	{
		//Given
		String sender = "Tony";
		//When
		User user = new User();
		user.setUserName(sender);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserName().equals(sender));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderName2()
	{
		//Given
		String sender = "Teo";
		//When
		User user = new User();
		user.setUserName(sender);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserName().equals(sender));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderName3()
	{
		//Given
		String sender = "Username";
		//When
		User user = new User();
		user.setUserName(sender);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserName().equals(sender));
	}
	
	@Test
	public void shouldCreateUserAndReturnEmail()
	{
		//Given
		String email = "email@teicm.gr";
		//When
		User user = new User();
		user.setUserEmail(email);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserEmail().equals(email));
	}
	
	@Test
	public void shouldCreateUserAndReturnEmail2()
	{
		//Given
		String email = "Papadopoulos@gmail.com";
		//When
		User user = new User();
		user.setUserEmail(email);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserEmail().equals(email));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderSex()
	{
		//Given
		String sex = "Female";
		//When
		User user = new User();
		user.setUserSex(sex);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserSex().equals(sex));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderSex2()
	{
		//Given
		String sex = "Male";
		//When
		User user = new User();
		user.setUserSex(sex);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserSex().equals(sex));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderCountry()
	{
		//Given
		String country = "Finland";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}

	@Test
	public void shouldCreateMessageAndReturnSenderCountry2()
	{
		//Given
		String country = "Greece";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderCountry3()
	{
		//Given
		String country = "Germany";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderCountry4()
	{
		//Given
		String country = "Swedden";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}
	
}
