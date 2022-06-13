package com.example.springboot.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.springboot.service.IndexService;

/**
 * @see https://tuhrig.de/using-configurationproperties-to-separate-service-and-configuration/
 */
//

@SpringJUnitConfig(classes = PropertesConfig.class, initializers = ConfigDataApplicationContextInitializer.class)
public class PropertesConfigTest2 {
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
        assertNull(service);
    }

}
