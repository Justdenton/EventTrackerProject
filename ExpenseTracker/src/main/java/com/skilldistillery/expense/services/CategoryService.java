package com.skilldistillery.expense.services;

import java.util.List;

import com.skilldistillery.expense.entities.Category;

public interface CategoryService {

	List<Category> getAllCategories();

	Category getCategoryById(int categoryId);

	Category create(Category category);

	Category update(int categoryId, Category category);

	boolean delete(int categoryId);

}
