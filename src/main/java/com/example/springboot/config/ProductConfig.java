package com.example.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.example.springboot.ProductInfo;

import lombok.Data;

// http://c.biancheng.net/spring_boot/config-bind.html

@Data
@Component
@ConfigurationProperties(prefix = "prod")
// @PropertySource("classpath:product.properties")
@PropertySource(value = "classpath:product.yml", factory = YamlPropertySourceFactory.class)
public class ProductConfig {
    String id;
    String name;
    ProductInfo info;
}
