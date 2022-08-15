package com.example.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
// @Import(PropertesConfig.class) //
// https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/html/using-boot-configuration-classes.html#using-boot-importing-configuration
@MapperScan("com.example.springboot.dao.mapper")
@Slf4j
public class Application implements ApplicationRunner, CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// https://www.tutorialspoint.com/spring_boot/spring_boot_runners.htm
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		log.info("Hello World from Application Runner");
		log.info("测试中文");
		log.info("日本語文字化け");
	}

	@Override
	public void run(String... arg0) throws Exception {
		log.info("Hello world from Command Line Runner");
	}

}
