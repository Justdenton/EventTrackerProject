package com.skilldistillery.expense.services;

import java.util.List;

import com.skilldistillery.expense.entities.Expense;

public interface ExpenseService {

	Expense getExpenseById(int expenseId);
	
	List<Expense> getAllExpenses();

	List<Expense> getExpensesByCategory(int categoryId);
	// List<Expense> getExpensesByCategoryName(String categoryName);

	List<Expense> getExpensesByPaymentMethod(int paymentMethodId);

	Expense create(Expense expense);
	Expense update(int expenseId, Expense updateExpense);
	boolean delete(int expenseId);

}
