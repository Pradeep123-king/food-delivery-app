package com.tap.dao;

import java.util.List;

import com.tap.model.User;

public interface UserDao {

    void addUser(User user);

    User getUser(int userId);

    List<User> getAllUser();

    void updateUser(User user);

    void deleteUser(int userId);



    RegisterResult registerUser(User user);
    
    public enum RegisterResult 
    {
        SUCCESS,
        EMAIL_EXISTS,
        ERROR
    }

    
}






















/*package com.tap.dao;

import java.util.List;

import com.tap.model.User;

public interface UserDao 
{
	User getUser(int userId);
	
	void addUser(User user);

    void updateUser(User user);

    void deleteUser(int userId);

    List<User> getAllUser();

}
*/