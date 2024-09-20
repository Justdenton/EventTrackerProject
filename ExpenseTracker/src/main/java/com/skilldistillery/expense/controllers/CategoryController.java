package com.skilldistillery.expense.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.expense.entities.Category;
import com.skilldistillery.expense.services.CategoryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping("{categoryId}")
	public Category getCategoryById(@PathVariable ("categoryId") int categoryId, HttpServletResponse res) {
		Category category = categoryService.getCategoryById(categoryId);
		if (category == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return category;
	}

	// CREATE
	@PostMapping
	public Category createCategory(@RequestBody Category category, HttpServletResponse res, HttpServletRequest req) {
		Category createdCategory = categoryService.create(category);
		if (createdCategory == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.setHeader("Location", url.append("/").append(createdCategory.getId()).toString());
		}
		return createdCategory;
	}

	// UPDATE
	@PutMapping("{categoryId}")
	public Category updateCategory(@PathVariable ("categoryId") int categoryId, @RequestBody Category category,
			HttpServletResponse res) {
		Category updatedCategory = categoryService.update(categoryId, category);
		if (updatedCategory == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return updatedCategory;
	}

	// DELETE
	@DeleteMapping("{categoryId}")
	public void deleteCategory(@PathVariable ("categoryId") int categoryId, HttpServletResponse res) {
		if (categoryService.delete(categoryId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}
	
}