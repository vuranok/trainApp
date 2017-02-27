package com.powerhouse.interview;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumptionRestController {

	@RequestMapping("/energy")
	public String helloWorld() {
		return "Hello world";
	}
}
