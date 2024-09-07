package com.skilldistillery.expense.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.expense.entities.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>{

}
