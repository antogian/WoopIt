package gr.teicm.icd.data.entities;

public class User {
	
	private Long userId;
	private String userName;
	private String userPass;
	private String userEmail;
	private String userSex;
	private String userCountry;
	private String userPhotoPath;
	
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
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + ", userEmail="
				+ userEmail + ", userSex=" + userSex + ", userCountry=" + userCountry + "]";
	}
	

}
