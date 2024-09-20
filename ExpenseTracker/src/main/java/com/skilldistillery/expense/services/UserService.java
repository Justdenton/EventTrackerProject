package com.skilldistillery.expense.services;

import java.util.List;

import com.skilldistillery.expense.entities.User;

public interface UserService {

	List<User> getAllUsers(); 
	User getUserById(int userId);

	User create(User user);
	User update(int userId, User updateUser);
	boolean delete(int userId);

	boolean deactivateUser(int userId);
	boolean activateUser(int userId);
	
	User authenticateUser(String username, String password);

}
