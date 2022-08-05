package com.example.springboot.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClockTest {
    @Test
    void testUTC() {
        log.info("Clock.systemUTC().instant() : " + Clock.systemUTC().instant());
        log.info("Clock.systemDefaultZone().instant() : " + Clock.systemDefaultZone().toString());
        log.info("LocalDateTime.now() : " + LocalDateTime.now());
        log.info("ZonedDateTime.now() : " + ZonedDateTime.now());
    }
}
