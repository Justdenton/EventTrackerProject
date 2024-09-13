package com.skilldistillery.expense.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class RecurringTransactionTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private RecurringTransaction recurringTransaction;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("ExpenseTrackerJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		recurringTransaction = em.find(RecurringTransaction.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close(); 
	}

	@Test
	void test_RecurringTransaction_entity_mapping() {
		assertNotNull(recurringTransaction);
		assertEquals("Monthly", recurringTransaction.getRecurPeriod());
		assertNotNull(recurringTransaction.getNextRecurDate());
	}

	@Test
	void test_RecurringTransaction_OneToOne_Expense_mapping() {
		assertNotNull(recurringTransaction); 
		Expense expense = recurringTransaction.getExpense();
		expense = em.find(Expense.class, 6);
		assertNotNull(recurringTransaction.getExpense()); 

		assertEquals(6, expense.getId()); 
		assertEquals(80.00, expense.getAmount()); 
		assertEquals("Monthly gym membership for Emily and I", expense.getDescription());
		assertEquals(recurringTransaction.getId(), expense.getRecurringTransaction().getId()); 
	}

}