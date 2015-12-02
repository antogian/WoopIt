package gr.teicm.icd.data.entities;

public class Message {
	
	private long id;
	private String body;
	private String time;
	private User sender;
	private double messageLatitude;
	private double messageLongitude;
	private int messageRadius;
	private int messageLikes;
	private int messageDislikes;
	
	
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

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
	
	public int getMessageLikes(){
		return this.messageLikes;
	}
	
	public void setMessageLikes(int messageLikes){
		this.messageLikes = messageLikes;
	}
	
	public int getMessageDislikes(){
		return this.messageDislikes;
	}
	
	public void setMessageDislikes(int messageDislikes){
		this.messageDislikes = messageDislikes;
	}
}