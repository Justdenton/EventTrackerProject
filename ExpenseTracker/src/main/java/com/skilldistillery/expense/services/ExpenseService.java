package com.skilldistillery.expense.services;

import java.util.List;

import com.skilldistillery.expense.entities.Expense;

public interface ExpenseService {

	List<Expense> getAllExpenses();

	List<Expense> getExpensesByCategory(int categoryId);
	List<Expense> getExpensesByPaymentMethod(int paymentId);

	Expense create(Expense expense);
	Expense update(int expenseId, Expense updateExpense);
	boolean delete(int expenseId); 

}
