package com.example;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/data")
	Map<String, String> plain() {
		return Map.of("name", "John Doe", "age", "42");
	}

}
