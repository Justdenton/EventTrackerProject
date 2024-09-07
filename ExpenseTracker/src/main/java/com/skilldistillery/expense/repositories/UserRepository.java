package com.skilldistillery.expense.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.expense.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	// List<User> findbyEnabledTrue();
	
	

}
