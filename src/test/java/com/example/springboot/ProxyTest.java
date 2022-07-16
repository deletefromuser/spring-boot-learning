package com.example.springboot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// @SpringBootTest
@Slf4j
public class ProxyTest {

    @Test
    void testConnectGFWAllowed() {
        RestTemplate restTemplate = new RestTemplate();
        log.info(restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", String.class));
    }

    @Test
    void testConnectGFWBlocked() throws IOException {
        // https://stackoverflow.com/a/52362981/19120213
        String hostname = "localhost"/* 127.0.0.1 */;
        int port = 1080;
        Proxy proxy = new Proxy(Proxy.Type.SOCKS,
                new InetSocketAddress(hostname, port));

        OkHttpClient client = new OkHttpClient.Builder()
                .proxy(proxy)
                .build();

        Request request = new Request.Builder()
                .url("https://nyaa.si/download/1437982.torrent")
                .build();

        try (Response response = client.newCall(request).execute()) {
            log.info(response.body().string());
        }
    }

}
