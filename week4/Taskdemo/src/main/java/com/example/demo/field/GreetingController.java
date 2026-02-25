package com.example.demo.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@Autowired
	private GreetingService greetingService;
	
	@GetMapping("/task4.2")
	public String showMessage() {
		String mes = greetingService.getMessage();
		
		System.out.println("=========================================");
		System.out.println("Task 4.2 :" + mes);
		System.out.println("=========================================");
		
		return mes;
	}
}
