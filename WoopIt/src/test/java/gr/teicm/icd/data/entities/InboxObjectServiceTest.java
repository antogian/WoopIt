package gr.teicm.icd.data.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class InboxObjectServiceTest {

	@Test
	public void shouldCreateInboxWithIdAndReturnTheCorrectId(){
		long id = 5L;
		Inbox inbox = new Inbox();
		inbox.setId(id);
		
		assertEquals(inbox.getId(), id);
	}

	@Test
	public void shouldCreateInboxWithDateAndReturnTheCorrectDate(){
		String date = "06/01/2016";
		Inbox inbox = new Inbox();
		inbox.setDate(date);
		
		assertEquals(inbox.getDate(), date);
	}
}
