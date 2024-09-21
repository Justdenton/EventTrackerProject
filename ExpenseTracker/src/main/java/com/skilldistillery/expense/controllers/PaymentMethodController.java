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

import com.skilldistillery.expense.entities.PaymentMethod;
import com.skilldistillery.expense.services.PaymentMethodService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api")
public class PaymentMethodController {

	@Autowired
	private PaymentMethodService paymentMethodService; 

	@GetMapping("payment-methods")
	public List<PaymentMethod> getAllPaymentMethods() {
		return paymentMethodService.getAllPaymentMethods();
	}

	@GetMapping("payment-methods/{paymentMethodId}")
	public PaymentMethod getPaymentMethodById(@PathVariable("paymentMethodId") int paymentMethodId,
			HttpServletResponse res) {
		PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(paymentMethodId);
		if (paymentMethod == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return paymentMethod;
	}

//	// CREATE
//	@PostMapping
//	public PaymentMethod createPaymentMethod(@RequestBody PaymentMethod paymentMethod, HttpServletResponse res,
//			HttpServletRequest req) {
//		PaymentMethod createdPaymentMethod = paymentMethodService.create(paymentMethod);
//		if (createdPaymentMethod == null) {
//			res.setStatus(400);
//		} else {
//			res.setStatus(201);
//			StringBuffer url = req.getRequestURL();
//			res.setHeader("Location", url.append("/").append(createdPaymentMethod.getId()).toString());
//		}
//		return createdPaymentMethod;
//	}
//
//	// UPDATE
//	// *** NULL PROBLEM ** 
//	// When updating, createdate is set to null - does it make sense to have a DT in here..?
//	@PutMapping("{paymentMethodId}")
//	public PaymentMethod updatePaymentMethod(@PathVariable("paymentMethodId") int paymentMethodId,
//			@RequestBody PaymentMethod paymentMethod, HttpServletResponse res) {
//		try {
//			PaymentMethod updatedPaymentMethod = paymentMethodService.update(paymentMethodId, paymentMethod);
//			if (updatedPaymentMethod == null) {
//				res.setStatus(404);
//			} else {
//				res.setStatus(200);
//			}
//			return updatedPaymentMethod;
//		} catch (Exception e) {
//			res.setStatus(400);
//			return null;
//		}
//	}
//
//	// DELETE
//	@DeleteMapping("{paymentMethodId}")
//	public void deletePaymentMethod(@PathVariable("paymentMethodId") int paymentMethodId, HttpServletResponse res) {
//		try {
//			boolean deleted = paymentMethodService.delete(paymentMethodId);
//			if (deleted) {
//				res.setStatus(204);
//			} else {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//		}
//	}

}