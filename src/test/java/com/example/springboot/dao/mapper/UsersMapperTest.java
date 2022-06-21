package com.example.springboot.dao.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.dao.model.User;

@SpringBootTest
public class UsersMapperTest {

    @Autowired
    UserMapper mapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testDeleteByPrimaryKey() {

    }

    @Test
    @Transactional
    void testInsert() {
        EasyRandom easyRandom = new EasyRandom();
        User person = easyRandom.nextObject(User.class);

        int count = jdbcTemplate.queryForObject("SELECT count(*) from `users`", Integer.class);
        assertEquals(0, count);

        count = mapper.insert(person);
        assertEquals(1, count);

        count = jdbcTemplate.queryForObject("SELECT count(*) from `users`", Integer.class);
        assertEquals(1, count);
    }

    @Test
    void testSelectByPrimaryKey() {

    }

    @Test
    void testUpdateByPrimaryKey() {

    }
}
