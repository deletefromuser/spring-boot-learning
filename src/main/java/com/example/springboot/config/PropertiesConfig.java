package com.example.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

// https://tuhrig.de/using-configurationproperties-to-separate-service-and-configuration/

@ConfigurationProperties(prefix = "product")
@Component
// https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/html/using-boot-auto-configuration.html#using-boot-auto-configuration
@EnableConfigurationProperties
@Data
public class PropertiesConfig {

    private String name;

    private String desc;

    private String details;
}