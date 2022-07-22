package com.example.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.UnexpectedRollbackException;

import com.example.springboot.dao.mapper.UserMapper;
import com.example.springboot.dao.model.User;
import com.example.springboot.dao.model.UserExample;

// spring.aop.proxy-target-class=false
@SpringBootTest
public class TransactionServiceNonProxyTest {
    @Autowired
    TransactionService service;

    @Autowired
    UserMapper mapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testInlineRequired() {
        service.start(6);
        int count = jdbcTemplate.queryForObject("SELECT count(*) from `users`", Integer.class);
        assertEquals(1, count);
    }

    @Test
    void testInlineNested() {
        service.start(16);
        int count = jdbcTemplate.queryForObject("SELECT count(*) from `users`", Integer.class);
        assertEquals(1, count);
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo("tom");
        List<User> persons = mapper.selectByExample(example);
        assertEquals(1, persons.size());
    }
}
