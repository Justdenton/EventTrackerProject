//package com.skilldistillery.expense.repositories;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.skilldistillery.expense.entities.Expense;
//
//@SpringBootTest
//class ExpenseRepositoryTest {
//	
//	// Find, exist, count (example from FilmRepoTest)
//	
//	@Autowired
//	private ExpenseRepository expenseRepo;
//
//	
////	@BeforeAll
////	static void setUpBeforeClass() throws Exception {
////	}
////	@AfterAll
////	static void tearDownAfterClass() throws Exception {
////	}
////	@BeforeEach
////	void setUp() throws Exception {
////	}
////	@AfterEach
////	void tearDown() throws Exception {
////	}
//
//	// 
//	
//	@Test
//	void test_findByCategoryId() {
//		List<Expense> expenses = expenseRepo.findByCategoryId(1);
//		assertNotNull(expenses);
//		assertTrue(expenses.size() > 0);
//	}
//	@Test
//	void test_findByPaymentMethodId() {
//		List<Expense> expenses = expenseRepo.findByPaymentMethodId(1);
//		assertNotNull(expenses);
//		assertTrue(expenses.size() > 0); 
//	}
//	
//	
//
//}
