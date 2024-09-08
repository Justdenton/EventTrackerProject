package com.skilldistillery.expense.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.expense.entities.Category;
import com.skilldistillery.expense.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public Category getCategoryById(int categoryId) {
		Optional<Category> categoryOpt = categoryRepo.findById(categoryId);
		if (categoryOpt.isPresent()) {
			return categoryOpt.get();
		}
		return null;
	}

	@Override
	public Category create(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public Category update(int categoryId, Category updateCategory) {
		Optional<Category> existingCategoryOpt = categoryRepo.findById(categoryId);
		if (existingCategoryOpt.isPresent()) {
			updateCategory.setId(categoryId);
			return categoryRepo.save(updateCategory);
		}
		return null;
	}

	@Override
	public boolean delete(int categoryId) {
		if (categoryRepo.existsById(categoryId)) {
			categoryRepo.deleteById(categoryId);
			return true;
		}
		return false;
	}

}
