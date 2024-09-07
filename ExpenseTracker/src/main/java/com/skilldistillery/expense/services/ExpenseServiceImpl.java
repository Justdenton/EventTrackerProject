package com.skilldistillery.expense.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.expense.entities.Expense;
import com.skilldistillery.expense.repositories.CategoryRepository;
import com.skilldistillery.expense.repositories.ExpenseRepository;
import com.skilldistillery.expense.repositories.PaymentMethodRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private PaymentMethodRepository paymentRepo;

	@Override
	public List<Expense> getAllExpenses() {
		return expenseRepo.findAll();
	}

	@Override
	public List<Expense> getExpensesByCategory(int categoryId) {
		if (categoryRepo.existsById(categoryId)) {
			return expenseRepo.findByCategoryId(categoryId);
		}
		return null;
	}

	@Override
	public List<Expense> getExpensesByPaymentMethod(int paymentId) {
		if (paymentRepo.existsById(paymentId)) {
			return expenseRepo.findByCategoryId(paymentId);
		}
		return null;
	}

	@Override
	public Expense create(Expense expense) {
		if (expense.getCategory() != null && !categoryRepo.existsById(expense.getCategory().getId())) {
			return null;
		}
		if (expense.getPaymentMethod() != null && !paymentRepo.existsById(expense.getPaymentMethod().getId())) {
			return null;
		}
		return expenseRepo.save(expense);
	}

	// CREATE
//	@Override
//	public Post createPost(Post post) {
//		if ( post.getCategory() == null) {
//			post.setCategory(em.find(Category.class, 5));
//			
//		}
//		em.persist(post);
//		return post;
//	}

	@Override
	public Expense update(int expenseId, Expense updateExpense) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int expenseId) {
		// TODO Auto-generated method stub
		return false;
	}

}
