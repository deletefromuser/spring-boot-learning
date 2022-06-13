package com.example.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

// https://tuhrig.de/using-configurationproperties-to-separate-service-and-configuration/

@ConfigurationProperties(prefix = "product")
@Component
@EnableConfigurationProperties
@Data
public class PropertesConfig {

    private String name;

    private String desc;

    private String details;
}