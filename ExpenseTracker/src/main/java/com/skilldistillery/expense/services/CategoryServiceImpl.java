package com.skilldistillery.expense.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.expense.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl {
	
	@Autowired
	private CategoryRepository categoryRepo;

}
