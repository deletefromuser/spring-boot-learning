package com.example.springboot.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springboot.service.IndexService;

// https://docs.spring.io/spring-boot/docs/2.0.6.RELEASE/reference/html/boot-features-testing.html#boot-features-configfileapplicationcontextinitializer-test-utility

@SpringBootTest
public class PropertesConfigTest {
    @Autowired
    private PropertesConfig config;

    @Autowired(required = false)
    private IndexService service;

    @Test
    void testGetName() {
        assertEquals("hello\\nworld", config.getName());
    }

    @Test
    void testGetDesc() {
        assertEquals("hello\\nworld", config.getDesc());
    }

    @Test
    void testGetDetails() {
        assertEquals("hello\nworld", config.getDetails());
    }

    @Test
    void testSpringBootTest() {
        assertNotNull(service);
    }

}
