package com.example.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dao.model.JpArticle;
import com.example.springboot.service.IndexService;
import com.example.springboot.service.LookupService;

@RestController
public class IndexController {
    private IndexService service;

    public IndexController(IndexService service) {
        this.service = service;
    }

    private LookupService lookupService;

    @GetMapping("/")
    public JpArticle index() {

        return service.index();
    }

    @GetMapping("/hello")
    public JpArticle hello() {

        return service.index();
    }
    // @GetMapping("/lookup")
    // public JpArticle hello(@RequestParam String user ) {
    //     return lookupService.findUser(user);
    // }

}
