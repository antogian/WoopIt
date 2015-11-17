package gr.teicm.icd.data.entities;

public class User {
	
	private Long userId;
	private String userName;
	private String userPass;
	private String userEmail;
	private String userSex;
	private String userCountry;
	private String userPhotoPath;
	private double userLatitude;
	private double userLongitude;
	private int userRadius;
	
	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserPass(){
		return userPass;
	}
	public void setUserPass(String userPass){
		this.userPass = userPass;
	}

	public String getUserEmail(){
		return userEmail;
	}
	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}
	
	public String getUserSex(){
		return userSex;
	}
	public void setUserSex(String userSex){
		this.userSex = userSex;
	}
	
	public String getUserCountry(){
		return userCountry;
	}
	public void setUserCountry(String userCountry){
		this.userCountry = userCountry;
	}
	
	public String getUserPhotoPath(){
		return userPhotoPath;
	}
	public void setUserPhotoPath(String userPhotoPath){
		this.userPhotoPath = userPhotoPath;
	}
	
	public double getUserLatitude(){
		return userLatitude;
	}
	public void setUserLatitude(double userLatitude){
		this.userLatitude = userLatitude;
	}
	
	public double getUserLongitude(){
		return userLongitude;
	}
	public void setUserLongitude(double userLongitude){
		this.userLongitude = userLongitude;
	}
	
	public int getUserRadius(){
		return userRadius;
	}
	public void setUserRadius(int userRadius){
		this.userRadius = userRadius;
	}
}
