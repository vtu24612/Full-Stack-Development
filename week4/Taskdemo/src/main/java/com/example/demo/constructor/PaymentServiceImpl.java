package com.example.demo.constructor;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Override
	public String pay() {
		return "4.3 : Constructor Injection";
	}
}
