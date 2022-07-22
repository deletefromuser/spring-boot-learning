package com.example.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.example.springboot.model.Todo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResumeTest {
    @Test
    void hashcodeTest() {
        log.info((new Todo()).hashCode() + "");
        assertEquals((new Todo()).hashCode(), (new Todo()).hashCode());
        log.info((new Todo("a")).hashCode() + "");
        log.info((new Todo("b")).hashCode() + "");
        assertNotEquals((new Todo("a")).hashCode(), (new Todo("b")).hashCode());
    }

    @Test
    void swapTwoNumber() {
        int a = 9, b = 11;
        log.info("{} - {}", a, b);
        a = a ^ b;
        b = a ^ b;
        a = b ^ a;
        log.info("{} - {}", a, b);
    }
}
