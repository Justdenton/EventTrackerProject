package com.skilldistillery.expense.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.expense.entities.RecurringTransaction;
import com.skilldistillery.expense.repositories.ExpenseRepository;
import com.skilldistillery.expense.repositories.RecurringTransactionRepository;

@Service
public class RecurringTransactionServiceImpl implements RecurringTransactionService {
	
	@Autowired
	private RecurringTransactionRepository recurringRepo;
	
	@Autowired
	private ExpenseRepository expenseRepo;  

	@Override
	public List<RecurringTransaction> getAllRecurringTransactions() {
		return recurringRepo.findAll();
	}

	@Override
	public RecurringTransaction getRecurringTransactionById(int recurringId) {
		Optional<RecurringTransaction> recurringOpt = recurringRepo.findById(recurringId);
		return recurringOpt.orElse(null);
	}

	@Override
	public RecurringTransaction create(RecurringTransaction recurringTransaction) {
		if (recurringTransaction.getExpense() == null) {
			return null; 
		}
		return recurringRepo.save(recurringTransaction);
		
	}

	@Override
	public RecurringTransaction update(int recurringTransactionId, RecurringTransaction updateRecurringTransaction) {
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int recurringTransactionId) {
		
		
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	

}
