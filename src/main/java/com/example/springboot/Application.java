package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.springboot.config.PropertiesConfig;

@SpringBootApplication
// @Import(PropertesConfig.class) // https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/html/using-boot-configuration-classes.html#using-boot-importing-configuration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
