package gr.teicm.icd.dao;

import gr.teicm.icd.data.entities.User;

public interface UserDAO {
    
    public void insert(User user);
    
    public void insertRole(User user);
    
    public void insertPhotoPath(User user);
    
    public String getPhotoPath(String userName);
    
    public void removePhoto(User user);
     
    public User findByUserId(Long userId);
    
    public Boolean checkIfUserNameExist(String userName);
}