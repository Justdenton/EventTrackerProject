package com.skilldistillery.expense.services;

import java.util.List;

import com.skilldistillery.expense.entities.User;

public interface UserService {
	
	List<User> getAllUsers();
	User showUser(int userId);
	User create(User user);
	User update(int userId, User updateUser);
	boolean delete(int userId); // or enable** Need to double check default behavior in database 
	

}
