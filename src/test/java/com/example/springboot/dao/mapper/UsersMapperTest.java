package com.example.springboot.dao.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    SqlSessionFactory sqlSessionFactory;

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
    void testBatchInsert() {
        EasyRandom easyRandom = new EasyRandom();

        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            for (int i = 0; i < 20; i++) {

                for (int j = 0; j < 50; j++) {
                    User person = easyRandom.nextObject(User.class);
                    person.setUsername(RandomStringUtils.randomAlphabetic(15));
                    person.setProvider(RandomStringUtils.randomAlphabetic(5));
                    mapper.insert(person);
                }
                sqlSession.commit();
            }

        }
    }
}
