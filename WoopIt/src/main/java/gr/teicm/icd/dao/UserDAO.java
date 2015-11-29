package gr.teicm.icd.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import gr.teicm.icd.data.entities.User;
@Repository
public interface UserDAO {
    
    public void insert(User user);
    
    public void insertRole(User user);
    
    public void insertPhotoPath(User user);
    
    public String getPhotoPath(String userName);
    
    public void removePhoto(User user);
     
    public User findByUserId(Long userId);
    
    public User getUserByName(String userName);
    
    public User getUserById (Long userId);
    
    public void addUserToFriends(User currentUser, User targetUser);
    
    public void addUserToBlocked(User currentUser, User targetUser);
    
    public void removeUserFromFriends(User currentUser, User targetUser);
    
    public void removeUserFromBlocked(User currentUser, User targetUser);
    
    public Boolean isFriend(User currentUser, User targetUser);
    
    public Boolean isBlocked(User currentUser, User targetUser);
    
    public List<User> getAllFriends(User currentUser);
    
    public List<User> getAllBlocked(User currentUser);
    
    public Boolean checkIfUserNameExist(String userName);
    
    public void insertGeoLocation(User user);
    
    public User getGeoLocation(String userName);
}