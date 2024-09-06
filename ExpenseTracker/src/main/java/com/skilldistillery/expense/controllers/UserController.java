package com.skilldistillery.expense.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.expense.entities.User;
import com.skilldistillery.expense.services.UserService;

@RestController   
@RequestMapping("api")
public class UserController {
	
	@Autowired  
	private UserService userService;
	
	@GetMapping("users")
	public List<User> getUserList(){
		return userService.getAllUsers();
	}
	
	

}
