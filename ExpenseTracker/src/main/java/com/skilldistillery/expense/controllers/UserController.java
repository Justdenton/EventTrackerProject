package com.skilldistillery.expense.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.expense.entities.User;
import com.skilldistillery.expense.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("{userId}")
	public User getUserById(@PathVariable("userId") int userId, HttpServletResponse res) {
		User user = userService.getUserById(userId);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

	// CREATE
	@PostMapping
	public User createUser(@RequestBody User user, HttpServletResponse res, HttpServletRequest req) {
		User createdUser = userService.create(user);
		if (createdUser == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.setHeader("Location", url.append("/").append(createdUser.getId()).toString());
		}
		return createdUser;
	}

	// UPDATE
	@PutMapping("{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User user, HttpServletResponse res) {
		try {
			User updatedUser = userService.update(userId, user);
			if (updatedUser == null) {
				res.setStatus(404);
			} else {
				res.setStatus(200);
			}
			return updatedUser;
		} catch (Exception e) {
			res.setStatus(400);
			return null;
		}
	}

	// DELETE
	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable("userId") int userId, HttpServletResponse res) {
		try {
			boolean deleted = userService.delete(userId);
			if (deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}

	// DEACTIVATE
	@PutMapping("{userId}/deactivate")
	public void deactivateUser(@PathVariable("userId") int userId, HttpServletResponse res) {
		boolean deactivated = userService.deactivateUser(userId);
		if (deactivated) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}
	}

	// ACTIVATE
	@PutMapping("{userId}/activate")
	public void activateUser(@PathVariable("userId") int userId, HttpServletResponse res) {
		boolean activated = userService.activateUser(userId);
		if (activated) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}
	}

}