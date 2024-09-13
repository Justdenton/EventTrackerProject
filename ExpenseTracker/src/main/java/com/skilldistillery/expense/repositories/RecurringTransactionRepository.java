package com.skilldistillery.expense.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.expense.entities.RecurringTransaction;

public interface RecurringTransactionRepository extends JpaRepository<RecurringTransaction, Integer>{

}
