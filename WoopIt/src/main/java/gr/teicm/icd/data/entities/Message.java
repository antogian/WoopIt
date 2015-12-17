package gr.teicm.icd.data.entities;


public class Message {
	
	private long id;
	private String body;
	private Long expiration;
	private User sender;
	private double messageLatitude;
	private double messageLongitude;
	private int messageRadius;
	private int messageLikes;
	private int messageDislikes;
	
	
	public Message()
	{
		this.body = "";
		this.expiration = 0L;
		this.sender = new User();
	}

	public Message(String body, Long expiration, User sender) 
	{
		this.body = body;
		this.expiration = expiration;
		this.sender = sender;
	}

	public long getId() 
	{
		return id;
	}
	
	public void setId(long id) 
	{
		this.id = id;
	}
	public String getBody() 
	{
		return body;
	}

	public Long getExpiration()
	{
		return this.expiration;
	}
	
	public User getSender() 
	{
		return sender;
	}

	public void setBody(String body) 
	{
		this.body = body;
	}
	
	public void setDuration(int duration)
	{
		Long currentTime = System.currentTimeMillis()/1000L;
		
		if(duration <= 0){
			duration = 30;
		}
		else if(duration > 1800){
			duration = 1800;
		}
		
		this.expiration = currentTime + duration;
	}
	
	public void setExpiration(Long expiration) 
	{
		this.expiration = expiration;
	}
	
	public void setSender(User sender) 
	{
		this.sender = sender;
	}
	
	public double getMessageLatitude()
	{
		return this.messageLatitude;
	}
	
	public void setMessageLatitude(double messageLatitude)
	{
		this.messageLatitude = messageLatitude;
	}
	
	public double getMessageLongitude()
	{
		return this.messageLongitude;
	}
	
	public void setMessageLongitude(double messageLongitude)
	{
		this.messageLongitude = messageLongitude;
	}
	
	public int getMessageRadius()
	{
		return this.messageRadius;
	}
	
	public void setMessageRadius(int messageRadius)
	{
		this.messageRadius = messageRadius;
	}
	
	public int getMessageLikes()
	{
		return this.messageLikes;
	}
	
	public void setMessageLikes(int messageLikes)
	{
		this.messageLikes = messageLikes;
	}
	
	public int getMessageDislikes()
	{
		return this.messageDislikes;
	}
	
	public void setMessageDislikes(int messageDislikes)
	{
		this.messageDislikes = messageDislikes;
	}
	
	public Boolean isExpired()
	{
		Long currentTime = System.currentTimeMillis()/1000L;
		
		return(currentTime > this.expiration);
	}
}