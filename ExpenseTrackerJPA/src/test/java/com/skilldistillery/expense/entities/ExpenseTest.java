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
		expense = em.find(Expense.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_Expense_entity_mapping() {
		assertNotNull(expense);
		assertEquals(10.10, expense.getAmount());
		assertEquals("Walmart - grocery and shoes", expense.getDescription());
	}

	@Test
	void test_Expense_ManyToOne_User() {
		assertNotNull(expense);
		assertNotNull(expense.getUser());
		assertEquals(1, expense.getUser().getId());
		assertEquals("Test", expense.getUser().getFirstName());
		assertEquals("Tester", expense.getUser().getLastName());
	}

	@Test
	void test_Expense_Category_mapping() {
		assertNotNull(expense);
		assertNotNull(expense.getCategory());
		assertEquals(2, expense.getCategory().getId());
		assertEquals("Grocery", expense.getCategory().getName());
	}

	@Test
	void test_Expense_PaymentMethod_mapping() {
		assertNotNull(expense);
		assertNotNull(expense.getPaymentMethod());
		assertEquals(1, expense.getPaymentMethod().getId());
		assertEquals("Cash", expense.getPaymentMethod().getName());
	}

}
