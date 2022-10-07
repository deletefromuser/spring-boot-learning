package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {

	@Operation(summary = "主页", description = "bar")
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@Operation(summary = "测试springdoc", description = "ruti")
	@GetMapping("/test-swagger")
	public String testSwagger() {
		return "Greetings from Spring Boot!";
	}

}