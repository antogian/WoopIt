package gr.teicm.icd.data.entities;

public class Message {
	
	private String body;
	private String time;
	private User sender;
	private double messageLatitude;
	private double messageLongitude;
	private int messageRadius;
	
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
	
	public double getMessageLatitude(){
		return this.messageLatitude;
	}
	
	public void setMessageLatitude(double messageLatitude){
		this.messageLatitude = messageLatitude;
	}
	
	public double getMessageLongitude(){
		return this.messageLongitude;
	}
	
	public void setMessageLongitude(double messageLongitude){
		this.messageLongitude = messageLongitude;
	}
	
	public int getMessageRadius(){
		return this.messageRadius;
	}
	
	public void setMessageRadius(int messageRadius){
		this.messageRadius = messageRadius;
	}
	
}