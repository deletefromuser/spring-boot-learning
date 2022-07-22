package com.example.springboot;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionManager;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TransactionTest {

    @Autowired
    DataSource ds;

    @Autowired
    TransactionManager tx;

    @Test
    void viewTxClass() {
        log.info(ds.getClass().toString());
        log.info(tx.getClass().toString());
    }

}
