package com.example.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

public class WebFluxTest {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Todo {
        private int userId;
        private int id;
        private String title;
        private boolean completed;
    }

    // https://www.baeldung.com/spring-5-webclient
    @Test
    void testWebClient() {
        WebClient client = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/todos/1")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                // .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();

        Mono<Todo> todo = client.get().exchangeToMono(response -> response.bodyToMono(Todo.class));
        assertNotNull(todo.block());
        assertNotNull(todo.block().getTitle());
    }
}
