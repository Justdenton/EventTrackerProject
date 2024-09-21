package com.skilldistillery.expense.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skilldistillery.expense.entities.RecurringTransaction;
import com.skilldistillery.expense.services.RecurringTransactionService;

@CrossOrigin({"*", "http://localhost/"}) 
@RestController
@RequestMapping("api")
public class RecurringTransactionController { 

    @Autowired
    private RecurringTransactionService recurringTransactionService;

    @GetMapping("recurring-transactions")
    public List<RecurringTransaction> getAllRecurringTransactions() {
        return recurringTransactionService.getAllRecurringTransactions();
    }
}