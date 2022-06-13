package com.example.springboot.service;

import org.springframework.stereotype.Service;

import com.example.springboot.config.PropertesConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IndexService {
    private PropertesConfig config;

    public IndexService(PropertesConfig config) {
        this.config = config;
    }

    public String index() {
        log.info("load PropertiesConfig successfully. -{}", config.toString());
        return "index";
    }
}
