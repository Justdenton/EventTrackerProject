package com.skilldistillery.expense.services;

import java.util.List;

import com.skilldistillery.expense.entities.RecurringTransaction;

public interface RecurringTransactionService {
	
	List<RecurringTransaction> getAllRecurringTransactions();

	RecurringTransaction getRecurringTransactionById(int recurringId); 
	// RecurringTransaction getRecurringTransactionBy_ExpenseId(int expenseId);  


	RecurringTransaction create(RecurringTransaction recurringTransaction);
	RecurringTransaction update(int recurringId, RecurringTransaction updateRecurringTransaction);
	boolean delete(int recurringId); 
	

}
