package com.skilldistillery.expense.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.expense.entities.User;
import com.skilldistillery.expense.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {
			return userOpt.get();
		}
		return null;
	}

	@Override
	public User create(User user) {
		return userRepo.save(user);
	}

	@Override
	public User update(int userId, User updateUser) {
		Optional<User> existingUserOpt = userRepo.findById(userId);
		if (existingUserOpt.isPresent()) {
			User existingUser = existingUserOpt.get();

			existingUser.setFirstName(updateUser.getFirstName());
			existingUser.setLastName(updateUser.getLastName());
			existingUser.setUsername(updateUser.getUsername());
			existingUser.setPassword(updateUser.getPassword());
			existingUser.setEmail(updateUser.getEmail());
			existingUser.setActive(updateUser.isActive());
			return userRepo.save(existingUser);
		}
		return null;
	}

	@Override
	public boolean delete(int userId) {
		if (userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			return true;
		}
		return false;
	}

	@Override
	public boolean deactivateUser(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			user.setActive(false);
			userRepo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean activateUser(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			user.setActive(true);
			userRepo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public User authenticateUser(String username, String password) {
		User user = userRepo.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}