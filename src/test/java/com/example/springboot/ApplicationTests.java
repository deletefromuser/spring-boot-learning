package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		Product paProduct = new Product();
		paProduct.setId("1");
		paProduct.setName("tomcat");
		paProduct.setInfo(new ProductInfo());
		paProduct.getInfo().setDetails("hooho");

		// System.out.println(new Gson().toJson(new Jackson2HashMapper(true).toHash(paProduct)));
		// System.out.println(new Gson().toJson(new Jackson2HashMapper(false).toHash(paProduct)));
	}

}
