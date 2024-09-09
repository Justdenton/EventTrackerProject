package com.skilldistillery.expense.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.expense.entities.PaymentMethod;
import com.skilldistillery.expense.repositories.PaymentMethodRepository;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	private PaymentMethodRepository paymentMethodRepo;

	@Override
	public List<PaymentMethod> getAllPaymentMethods() {
		return paymentMethodRepo.findAll();
	}

	@Override
	public PaymentMethod getPaymentMethodById(int paymentMethodId) {
		Optional<PaymentMethod> paymentMethodOpt = paymentMethodRepo.findById(paymentMethodId);
		if (paymentMethodOpt.isPresent()) {
			return paymentMethodOpt.get();
		}
		return null;
	}

	@Override
	public PaymentMethod create(PaymentMethod paymentMethod) {
		return paymentMethodRepo.save(paymentMethod);
	}

	@Override
	public PaymentMethod update(int paymentMethodId, PaymentMethod paymentMethod) {
		Optional<PaymentMethod> existingPaymentMethodOpt = paymentMethodRepo.findById(paymentMethodId);
		if (existingPaymentMethodOpt.isPresent()) {
			paymentMethod.setId(paymentMethodId);
			return paymentMethodRepo.save(paymentMethod);
		}
		return null;
	}

	@Override
	public boolean delete(int paymentMethodId) {
		if (paymentMethodRepo.existsById(paymentMethodId)) {
			paymentMethodRepo.deleteById(paymentMethodId);
			return true;
		}
		return false;
	}
}