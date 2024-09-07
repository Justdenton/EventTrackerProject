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
	private PaymentMethodRepository pmRepo;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expense create(Expense expense) {
		// TODO Auto-generated method stub
		return null;
	}

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
