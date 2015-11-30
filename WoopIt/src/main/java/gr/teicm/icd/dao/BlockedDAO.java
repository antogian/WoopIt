package gr.teicm.icd.dao;

import java.util.List;

import gr.teicm.icd.data.entities.User;

public interface BlockedDAO {
	
    public void addUserToBlocked(User currentUser, User targetUser);
    
    public void removeUserFromBlocked(User currentUser, User targetUser);
    
    public Boolean isBlocked(User currentUser, User targetUser);
    
    public List<User> getAllBlocked(User currentUser);
	
}
