package com.example.springboot.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class LookupServiceTest {

    @Autowired
    private LookupService lookupService;

    @Test
    public void testFindUser() throws InterruptedException, ExecutionException {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<String> page1 = lookupService.findUser("1");
        CompletableFuture<String> page2 = lookupService.findUser("5");
        CompletableFuture<String> page3 = lookupService.findUser("10");

        // Wait until they are all done
        // join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行
        CompletableFuture.allOf(page1, page2, page3).join();

        // Print results, including elapsed time
        float exc = (float) (System.currentTimeMillis() - start) / 1000;
        log.info("Elapsed time: " + exc + " seconds");
        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());
    }
    
}
