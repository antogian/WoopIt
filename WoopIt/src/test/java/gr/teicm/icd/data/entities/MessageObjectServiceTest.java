package gr.teicm.icd.data.entities;

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
	public void shouldCreateUserAndReturnEmail3()
	{
		//Given
		String email = "theodoulidis@gmail.com";
		//When
		User user = new User();
		user.setUserEmail(email);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserEmail().equals(email));
	}
	
	@Test
	public void shouldCreateUserAndReturnEmail4()
	{
		//Given
		String email = "Papadimitriou@gmail.com";
		//When
		User user = new User();
		user.setUserEmail(email);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserEmail().equals(email));
	}
	
	@Test
	public void shouldCreateUserAndReturnEmail5()
	{
		//Given
		String email = "Theodoros@gmail.com";
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
	
	@Test
	public void shouldCreateMessageAndReturnSenderCountry5()
	{
		//Given
		String country = "England";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderCountry6()
	{
		//Given
		String country = "USA";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderCountry7()
	{
		//Given
		String country = "Albania";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderCountry8()
	{
		//Given
		String country = "Cyprus";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderCountry9()
	{
		//Given
		String country = "Skopia";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderCountry10()
	{
		//Given
		String country = "New Zeland";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}
	
	@Test
	public void shouldCreateMessageAndReturnSenderCountry11()
	{
		//Given
		String country = "Poland";
		//When
		User user = new User();
		user.setUserCountry(country);
		Message msg = new Message();
		msg.setSender(user);
		//Then
		Assert.assertTrue(msg.getSender().getUserCountry().equals(country));
	}
	
	@Test
	public void shouldCreateMessageWith5LikesAndReturnMessageWith5Likes()
	{
		//Given
		int messageLikes = 5;
		//When
		Message msg = new Message();
		msg.setMessageLikes(messageLikes);
		//Then
		Assert.assertEquals(msg.getMessageLikes(), messageLikes);
	}
	
	@Test
	public void shouldCreateMessageWith5DisLikesAndReturnMessageWith5DisLikes()
	{
		//Given
		int messageDislikes = 5;
		//When
		Message msg = new Message();
		msg.setMessageDislikes(messageDislikes);
		//Then
		Assert.assertEquals(msg.getMessageDislikes(), messageDislikes);
	}
	
	@Test
	public void shouldCreateMessageWithDurationLessThanZeroAndReturnMessageWithDuration30Seconds() {
		//Given
		int duration = 0;
		//When
		Message msg = new Message();
		msg.setDuration(duration);
		//Then
		Assert.assertEquals(msg.getExpiration(), System.currentTimeMillis()/1000L+duration, 1000);
	}
	
	@Test
	public void shouldCreateMessageWithDurationMoreThan1800AndReturnMessageWithDuration1800Seconds() {
		//Given
		int duration = 3000;
		//When
		Message msg = new Message();
		msg.setDuration(duration);
		//Then
		Assert.assertEquals(msg.getExpiration(), System.currentTimeMillis()/1000L+1800, 1);
	}
	
	@Test
	public void shouldCreateMessageWithGivenParameters(){
		String body = "body";
		Long expiration = 1800L;
		User sender = new User();
		sender.setUserName("testUser");
		
		Message msg = new Message(body, expiration, sender);
		Assert.assertEquals(msg.getExpiration(), 1800L, 0);
		Assert.assertEquals(msg.getBody(), "body");
		Assert.assertEquals(msg.getSender().getUserName(), "testUser");
	}
	
	@Test
	public void shouldCreateExpiredMessageAndReturnTue(){
		Message msg = new Message();
		msg.setExpiration(0L);
		Assert.assertTrue(msg.isExpired());
		
	}
}
