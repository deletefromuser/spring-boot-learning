package com.example.springboot.service;

import org.jeasy.random.EasyRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.config.ProductConfig;
import com.example.springboot.config.PropertiesConfig;
import com.example.springboot.dao.mapper.JpArticleMapper;
import com.example.springboot.dao.model.JpArticle;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IndexService {
    private PropertiesConfig config;

    private ProductConfig product;

    @Autowired
    JpArticleMapper mapper;

    public IndexService(PropertiesConfig config, ProductConfig product) {
        this.config = config;
        this.product = product;
    }

    public JpArticle index() {
        log.info("load PropertiesConfig successfully. -{}", config.toString());
        log.info("load ProductConfig successfully. -{}", product.toString());
        
        JpArticle article = new JpArticle();
        article.setId(4L);
        article.setTitle("null3333333");
        mapper.insert(article);
        return mapper.selectByPrimaryKey(4L);

        // return new EasyRandom().nextObject(JpArticle.class);
    }
}
