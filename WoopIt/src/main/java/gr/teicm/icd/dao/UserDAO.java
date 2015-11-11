package gr.teicm.icd.dao;

import gr.teicm.icd.data.entities.User;

public interface UserDAO {
    
    public void insert(User user);
    
    public void insertRole(User user);
     
    public User findByUserId(Long userId);
    
    public Boolean checkIfUserNameExist(String userName);
}