package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springboot.service.IndexService;

@Controller
public class IndexController {
    private IndexService service;

    public IndexController(IndexService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {

        return service.index();
    }
}
