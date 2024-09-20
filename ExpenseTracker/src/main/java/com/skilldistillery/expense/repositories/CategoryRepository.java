package com.skilldistillery.expense.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.expense.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	 

}
