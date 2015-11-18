package gr.teicm.icd.dao;

import gr.teicm.icd.data.entities.User;

public interface UserDAO {
    
    public void insert(User user);
    
    public void insertRole(User user);
    
    public void insertPhotoPath(User user);
    
    public String getPhotoPath(String userName);
    
    public void removePhoto(User user);
     
    public User findByUserId(Long userId);
    
    public User getUserByName(String userName);
    
    public void addUserToFriends(User currentUser, User targetUser);
    
    public void addUserToUnwanted(User currentUser, User targetUser);
    
    public void removeUserFromFriends(User currentUser, User targetUser);
    
    public void removeUserFromUnwanted(User currentUser, User targetUser);
    
    public Boolean isFriend(User currentUser, User targetUser);
    
    public Boolean isUnwanted(User currentUser, User targetUser);
    
    public Boolean checkIfUserNameExist(String userName);
}