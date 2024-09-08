package com.skilldistillery.expense.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.expense.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	List<Expense> findByCategoryId(int categoryId);

	List<Expense> findByPaymentMethodId(int paymentMethodId);

}
