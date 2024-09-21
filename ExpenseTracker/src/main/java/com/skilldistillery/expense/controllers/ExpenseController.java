package com.skilldistillery.expense.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.expense.entities.Expense;
import com.skilldistillery.expense.services.ExpenseService;
import com.skilldistillery.expense.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin({ "*", "http://localhost/" })
@RestController
@RequestMapping("api")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private UserService userService;

	// http://localhost:8086/api/expenses
	@GetMapping({ "expenses", "expenses/" })
	public List<Expense> getExpenseList() {
		return expenseService.getAllExpenses();
	}

	@GetMapping("users/{userId}/expenses")
	public List<Expense> getExpensesByUserId(@PathVariable("userId") int userId, HttpServletResponse res) {
		List<Expense> expenses = expenseService.getExpensesByUserId(userId);
		if (expenses == null || expenses.isEmpty()) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return expenses; 
	}

	@GetMapping("expenses/{expenseId}")
	public Expense expenseSearch(@PathVariable("expenseId") int expenseId, HttpServletResponse res) {
		Expense expense = expenseService.getExpenseById(expenseId);
		if (expense == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return expense;
	}

	@GetMapping("expenses/categories/{categoryId}")
	public List<Expense> categorySearch(@PathVariable("categoryId") int categoryId, HttpServletResponse res) {
		List<Expense> expenses = expenseService.getExpensesByCategory(categoryId);
		if (expenses == null || expenses.isEmpty()) {
			res.setStatus(404);
			return null;
		} else {
			res.setStatus(200);
			return expenses;
		}
	}

	@GetMapping("expenses/payments/{paymentMethodId}")
	public List<Expense> paymentSearch(@PathVariable("paymentMethodId") int paymentMethodId, HttpServletResponse res) {
		List<Expense> expenses = expenseService.getExpensesByPaymentMethod(paymentMethodId);
		if (expenses == null || expenses.isEmpty()) {
			res.setStatus(404);
			return null;
		} else {
			res.setStatus(200);
			return expenses;
		}
	}

	// CREATE
	@PostMapping("expenses")
	public Expense createExpense(@RequestBody Expense expense, HttpServletResponse res, HttpServletRequest req) {

		if (expense.getUser() == null || userService.getUserById(expense.getUser().getId()) == null) {
			res.setStatus(400);
			return null;
		}

		Expense createdExpense = expenseService.create(expense);
		if (createdExpense == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.setHeader("Location", url.append("/").append(createdExpense.getId()).toString());
		}
		return createdExpense;
	}

	// UPDATE
	@PutMapping("expenses/{expenseId}")
	public Expense updateExpense(@PathVariable("expenseId") int expenseId, @RequestBody Expense expense,
			HttpServletResponse res) {
		try {
			Expense updatedExpense = expenseService.update(expenseId, expense);
			if (updatedExpense == null) {
				res.setStatus(404);
			} else {
				res.setStatus(200);
			}
			return updatedExpense;
		} catch (Exception e) {
			res.setStatus(400);
			return null;
		}
	}

	// DELETE
	@DeleteMapping("expenses/{expenseId}")
	public void deleteExpense(@PathVariable("expenseId") int expenseId, HttpServletResponse res) {
		try {
			boolean deleted = expenseService.delete(expenseId);
			if (deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

	// DISABLE
	@PutMapping("expenses/{expenseId}/disable")
	public Expense disableExpense(@PathVariable("expenseId") int expenseId, @RequestBody Expense expense,
			HttpServletResponse res) {
		try {
			Expense existingExpense = expenseService.getExpenseById(expenseId);
			if (existingExpense == null) {
				res.setStatus(404);
				return null;
			}
			existingExpense.setEnabled(expense.getEnabled());
			Expense updatedExpense = expenseService.update(expenseId, existingExpense);
			res.setStatus(200);
			return updatedExpense;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}

}
