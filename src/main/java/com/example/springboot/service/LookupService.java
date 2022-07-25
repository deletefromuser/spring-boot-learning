package com.example.springboot.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LookupService {
    private RestTemplate restTemplate = new RestTemplate();

    // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行(并指定线程池的名字)
    @Async("taskExecutor")
    public CompletableFuture<String> findUser(String user) throws InterruptedException {
        log.info("Looking up " + user);
        String url = String.format("https://jsonplaceholder.typicode.com/users/%s", user);
        String results = restTemplate.getForObject(url, String.class);
        // Artificial delay of 3s for demonstration purposes
        Thread.sleep(3000L);
        return CompletableFuture.completedFuture(results);
    }
}
