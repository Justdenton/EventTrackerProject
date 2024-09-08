package com.skilldistillery.expense.services;

import java.util.List;
import java.util.Optional;

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
	public Expense getExpenseById(int expenseId) {
		Optional<Expense> expenseOpt = expenseRepo.findById(expenseId);
		return expenseOpt.orElse(null);
	}

	@Override
	public List<Expense> getExpensesByCategory(int categoryId) {
		if (categoryRepo.existsById(categoryId)) {
			return expenseRepo.findByCategoryId(categoryId);
		}
		return null;
	}

//	@Override
//	public List<Expense> getExpensesByCategoryName(String categoryName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Expense> getExpensesByPaymentMethod(int paymentMethodId) {
		if (paymentRepo.existsById(paymentMethodId)) {
			return expenseRepo.findByPaymentMethodId(paymentMethodId);
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

	@Override
	public Expense update(int expenseId, Expense updateExpense) {
		Optional<Expense> existingExpenseOpt = expenseRepo.findById(expenseId);
		if (existingExpenseOpt.isPresent()) {
			Expense existingExpense = existingExpenseOpt.get();

			// Create Time bug in JSON (null issue in console) 
			updateExpense.setCreateTime(existingExpense.getCreateTime());
			updateExpense.setId(expenseId);
			return expenseRepo.save(updateExpense);
		}
		return null; 
	}

	@Override
	public boolean delete(int expenseId) {
		if (expenseRepo.existsById(expenseId)) {
			expenseRepo.deleteById(expenseId);
			return true;
		}
		return false;
	}

}
