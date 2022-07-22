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

@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    TransactionService service;

    @Autowired
    UserMapper mapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testRequired() {
        assertThrows(UnexpectedRollbackException.class, () -> service.start(0));
    }

    @Test
    void testNested() {
        service.start(6);
        int count = jdbcTemplate.queryForObject("SELECT count(*) from `users`", Integer.class);
        assertEquals(1, count);
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo("tom");
        List<User> persons = mapper.selectByExample(example);
        assertEquals(1, persons.size());
    }

    @Test
    void testInlineRequired() {
        service.start(6);
        int count = jdbcTemplate.queryForObject("SELECT count(*) from `users`", Integer.class);
        assertEquals(1, count);
    }

    /**
     * <code>
     * The default advice mode for processing @Transactional annotations is proxy,
     * which allows for interception of calls through the proxy only. Local calls
     * within the same class cannot get intercepted that way. For a more advanced
     * mode of interception, consider switching to aspectj mode in combination with
     * compile-time or load-time weaving.
     * <br>
     * The proxy-target-class attribute controls what type of transactional proxies
     * are created for classes annotated with the @Transactional annotation. If
     * proxy-target-class is set to true, class-based proxies are created. If
     * proxy-target-class is false or if the attribute is omitted, standard JDK
     * interface-based proxies are created. (See Proxying Mechanisms for a
     * discussion of the different proxy types.)
     * <code>
     * 
     * @see https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#transaction-declarative-annotations
     * @see https://stackoverflow.com/questions/37217075/spring-nested-transactions
     */
    @Test
    @Disabled
    void testInlineNested() {
        service.start(16);
        int count = jdbcTemplate.queryForObject("SELECT count(*) from `users`", Integer.class);
        assertEquals(1, count);
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo("tom");
        List<User> persons = mapper.selectByExample(example);
        assertEquals(1, persons.size());
    }

    @Test
    void testRequiredNew() {
        service.start(3);
        int count = jdbcTemplate.queryForObject("SELECT count(*) from `users`", Integer.class);
        assertEquals(1, count);
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo("tom");
        List<User> persons = mapper.selectByExample(example);
        assertEquals(1, persons.size());
    }
}
