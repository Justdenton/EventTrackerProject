package com.skilldistillery.expense.services;

import java.util.List;

import com.skilldistillery.expense.entities.Expense;

public interface ExpenseService {

	List<Expense> getAllExpenses();

	Expense getExpenseById(int expenseId);
	
	List<Expense> getExpensesByCategory(int categoryId);
	// List<Expense> getExpensesByCategoryName(String categoryName);

	List<Expense> getExpensesByPaymentMethod(int paymentMethodId);
	
	List<Expense> getExpensesByUserId(int userId);

	Expense create(Expense expense);
	Expense update(int expenseId, Expense updateExpense);
	boolean delete(int expenseId);

}
