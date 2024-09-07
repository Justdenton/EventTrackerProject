package com.skilldistillery.expense.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.expense.entities.User;
import com.skilldistillery.expense.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
//	@Override
//	public List<User> getAllUsersEnabled(){
//		return userRepo.findbyEnabledTrue();
//	}

	@Override
	public User showUser(int userId) {
		// TODO Auto-generated method stub 
		return null;
	}

	@Override
	public User create(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(int userId, User updateUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int userId) {
		// TODO Auto-generated method stub
		return false;
	}



}
