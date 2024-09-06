package com.skilldistillery.expense.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.expense.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	

}
