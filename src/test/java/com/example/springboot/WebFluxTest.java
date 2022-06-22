package com.example.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.springboot.model.Todo;

import reactor.core.publisher.Mono;

public class WebFluxTest {

    // https://www.baeldung.com/spring-5-webclient
    @Test
    void testWebClient() {
        WebClient client = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/todos/1")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                // .defaultUriVariables(Collections.singletonMap("url",
                // "http://localhost:8080"))
                .build();

        Mono<Todo> todo = client.get().exchangeToMono(response -> response.bodyToMono(Todo.class));
        assertNotNull(todo.block());
        assertNotNull(todo.block().getTitle());
    }
}
