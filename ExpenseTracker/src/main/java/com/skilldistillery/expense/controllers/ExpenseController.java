package com.skilldistillery.expense.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.expense.entities.Expense;
import com.skilldistillery.expense.services.ExpenseService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	//	http://localhost:8086/api/expenses 
	@GetMapping("expenses")
	public List<Expense> getExpenseList(){
		return expenseService.getAllExpenses();
	}
	
	// http://localhost:8086/api/expenses/categories/4
	@GetMapping("expenses/categories/{categoryId}")
	public List<Expense> categorySearch(@PathVariable("categoryId") int categoryId, HttpServletResponse res){
		List<Expense> expenses = expenseService.getExpensesByCategory(categoryId);
		if (expenses == null || expenses.isEmpty()) {
			res.setStatus(404);
		}
		res.setStatus(200);
		return expenses;
	}
	
	
	
	
}
