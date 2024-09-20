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

class PaymentMethodTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private PaymentMethod pm;

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
		pm = em.find(PaymentMethod.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_PaymentMethod_entity_mapping() {
		assertNotNull(pm);
		assertEquals(1, pm.getId());
		assertEquals("Cash", pm.getName());
		// assertTrue(pm.getEnabled());
	}

}
