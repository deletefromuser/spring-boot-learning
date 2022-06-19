package com.example.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.hash.Jackson2HashMapper;

import com.google.gson.Gson;

@SpringBootTest
class ApplicationTests {

	@Value("${spring.security.oauth2.client.registration.google.clientId}")
	private String clientId;

	@Test
	void contextLoads() {
		Product paProduct = new Product();
		paProduct.setId("1");
		paProduct.setName("tomcat");
		paProduct.setInfo(new ProductInfo());
		paProduct.getInfo().setDetails("hooho");

		System.out.println(new Gson().toJson(new Jackson2HashMapper(true).toHash(paProduct)));
		System.out.println(new Gson().toJson(new Jackson2HashMapper(false).toHash(paProduct)));
	}

	@Test
	void testValue() {
		assertNotNull(clientId);
	}

}
