package com.example.springboot.listener;

import org.springframework.stereotype.Component;

import com.example.springboot.model.Todo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TodoListener {

    // @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Todo todo) {
        log.info(todo.toString());
    }

}
