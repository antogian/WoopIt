package gr.teicm.icd.dao;

import java.util.List;
import gr.teicm.icd.data.entities.User;

public interface UserDAO {
    
    public void insert(User user);
    
    public void insertRole(User user);
    
    public void insertPhotoPath(User user);
    
    public String getPhotoPath(String userName);
    
    public void removePhoto(User user);
     
    public User findByUserId(Long userId);
    
    public User getUserByName(String userName);
    
    public User getUserById (Long userId);
    
    public Boolean checkIfUserNameExist(String userName);
    
    public Boolean checkIfEmailExist(String eMail);
    
    public void insertGeoLocation(User user);
    
    public User getGeoLocation(String userName);
    
    public boolean userExists(String keyword);
    
    public List<User> searchUsersByKeyword(String keyword);

}