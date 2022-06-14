package com.example.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.example.springboot.ProductInfo;

import lombok.Data;

/**
 * @see http://c.biancheng.net/spring_boot/config-bind.html
 * @see https://www.baeldung.com/spring-yaml-propertysource
 */

@Data
@Component
@ConfigurationProperties(prefix = "prod")
// @PropertySource("classpath:product.properties") // use properties
@PropertySource(value = "classpath:product.yml", factory = YamlPropertySourceFactory.class) // use yaml
public class ProductConfig {
    String id;
    String name;
    ProductInfo info;
}
