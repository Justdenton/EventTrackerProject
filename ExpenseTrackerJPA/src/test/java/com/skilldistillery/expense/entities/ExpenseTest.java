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

class ExpenseTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Expense expense;

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
		// expense = em.find(Expense.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_Expense_entity_mapping() {
		expense = em.find(Expense.class, 6);
		assertNotNull(expense);
		assertEquals(80.00, expense.getAmount());
		assertEquals("Monthly gym membership for Emily and I", expense.getDescription());
	}

	@Test
	void test_Expense_ManyToOne_User_mapping() {
		expense = em.find(Expense.class, 6);
		assertNotNull(expense);
		assertNotNull(expense.getUser());
		assertEquals(2, expense.getUser().getId());
		assertEquals("Justin", expense.getUser().getFirstName());
		assertEquals("Denton", expense.getUser().getLastName());
	}

	@Test
	void test_Expense_ManyToOne_Category_mapping() {
		expense = em.find(Expense.class, 6);
		assertNotNull(expense);
		assertNotNull(expense.getCategory());
		assertEquals(6, expense.getCategory().getId());
		assertEquals("Health", expense.getCategory().getName());
	}

	@Test
	void test_Expense_ManyToOne_PaymentMethod_mapping() {
		expense = em.find(Expense.class, 6);
		assertNotNull(expense);
		assertNotNull(expense.getPaymentMethod());
		assertEquals(4, expense.getPaymentMethod().getId());
		assertEquals("Debit Card", expense.getPaymentMethod().getName());
	}

	@Test
	void test_Expense_OneToOne_RecurringTransaction_mapping() {
		expense = em.find(Expense.class, 6);
		assertNotNull(expense);
		assertNotNull(expense.getRecurringTransaction());

		RecurringTransaction recurringTransaction = expense.getRecurringTransaction();
		assertEquals(1, recurringTransaction.getId());
		assertNotNull(recurringTransaction); 
		assertEquals(1, recurringTransaction.getId()); 
		assertEquals("Monthly", recurringTransaction.getRecurPeriod()); 
		assertEquals(expense.getId(), recurringTransaction.getExpense().getId());
	}
	

}
