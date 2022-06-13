package com.example.springboot.service;

import org.springframework.stereotype.Service;

import com.example.springboot.config.ProductConfig;
import com.example.springboot.config.PropertiesConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IndexService {
    private PropertiesConfig config;

    private ProductConfig product;

    public IndexService(PropertiesConfig config, ProductConfig product) {
        this.config = config;
        this.product = product;
    }

    public String index() {
        log.info("load PropertiesConfig successfully. -{}", config.toString());
        log.info("load ProductConfig successfully. -{}", product.toString());
        return "index";
    }
}
