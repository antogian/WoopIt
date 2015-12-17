package gr.teicm.icd.dao;

import java.util.List;

import gr.teicm.icd.data.entities.User;

public interface FriendDAO {
	
    public void addUserToFriends(User currentUser, User targetUser);
    
    public void removeUserFromFriends(User currentUser, User targetUser);
    
    public Boolean isFriend(User currentUser, User targetUser);
    
    public List<User> getAllFriends(User currentUser);
    
}
