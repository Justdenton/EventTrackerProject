package com.skilldistillery.expense.services;

import java.util.List;

import com.skilldistillery.expense.entities.Expense;

public interface ExpenseService {

	List<Expense> getAllExpenses();

	// NEED TO FIX - should be - String category? 
		//	http://localhost:8086/api/expenses/categories/4		VS		http://localhost:8086/api/expenses/categories/food				USER EXPERIENCE
	List<Expense> getExpensesByCategory(int categoryId);
	List<Expense> getExpensesByPaymentMethod(int paymentId);

	Expense create(Expense expense);
	Expense update(int expenseId, Expense updateExpense);
	boolean delete(int expenseId); 

}
