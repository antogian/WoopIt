package gr.teicm.icd.data.entities;

public class Message {
	
	private String body;
	private String time;
	private User sender;
	
	public Message(){
		this.body = "";
		this.time = "";
		this.sender = new User();
	}

	public Message(String body, String time, User sender) {
		this.body = body;
		this.time = time;
		this.sender = sender;
	}

	public String getBody() {
		return body;
	}

	public String getTime(){
		return this.time;
	}
	
	public User getSender() {
		return sender;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setTime(String time){
		this.time = time;
	}
	
	public void setSender(User sender) {
		this.sender = sender;
	}
	
}