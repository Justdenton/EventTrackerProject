package com.skilldistillery.expense.services;

import java.util.List;

import com.skilldistillery.expense.entities.PaymentMethod;

public interface PaymentMethodService {

	List<PaymentMethod> getAllPaymentMethods();

	PaymentMethod getPaymentMethodById(int paymentMethodId);

	PaymentMethod create(PaymentMethod paymentMethod);
	PaymentMethod update(int paymentMethodId, PaymentMethod paymentMethod);
	boolean delete(int paymentMethodId); 

}
