package com.example.springboot.dao.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sql.DataSource;

import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.dao.model.User;

@SpringBootTest
public class UsersMapperTest {

    @Autowired
    UserMapper mapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    // @Autowired
    // DataSource dataSource;

    private Table table;

    @BeforeEach
    void setUp() {
        table = new Table(jdbcTemplate.getDataSource(), "users");
    }

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
    @Transactional
    void testSelectByPrimaryKey() {
        // jdbcTemplate.update("delete from `users`"); // perhaps not work for
        // datasource
        EasyRandom easyRandom = new EasyRandom();
        User person = easyRandom.nextObject(User.class);
        person.setUsername("tom");
        mapper.insert(person);

        Assertions.assertThat(table).column("username")
                .value().isEqualTo("deletefromuser")
                .value().isEqualTo("deletefromuserPBztM7");
    }

    @Test
    void testUpdateByPrimaryKey() {

    }
}
