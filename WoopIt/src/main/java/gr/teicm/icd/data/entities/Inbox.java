package gr.teicm.icd.data.entities;

public class Inbox {
	
	private long id;
	private User receiverUser;
	private User senderUser;
	private String body;
	private String date;
	
	public long getId(){
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public User getReceiverUser(){
		return this.receiverUser;
	}
	
	public void setReceiverUser(User receiverUser){
		this.receiverUser = receiverUser;
	}
	
	public User getSenderUser(){
		return this.senderUser;
	}
	
	public void setSenderUser(User senderUser){
		this.senderUser = senderUser;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
