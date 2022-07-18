package com.example.springboot.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.springboot.model.Todo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TodoListener {

    @RabbitListener(queues = "spring-boot")
    public void receiveOrder(Todo todo) {
        log.info("queues=spring-boot:" + todo.toString());
    }

    @RabbitListener(queues = { "spring-boot-direct", "spring-boot-direct-2" })
    public void receiveDirect(Todo todo) {
        log.info("queues=spring-boot-direct,spring-boot-direct-2:" + todo.toString());
    }

    @RabbitListener(queues = "spring-boot-specific")
    public void receiveOrderSpecific(Todo todo) {
        log.info("queues=spring-boot-specific:" + todo.toString());
    }

    @RabbitListener(queues = { "fanout.1" })
    public void receiveOrderFanout1(Todo todo) {
        log.info("queues=fanout.1:" + todo.toString());
    }

    @RabbitListener(queues = { "fanout.2" })
    public void receiveOrderFanout2(Todo todo) {
        log.info("queues=fanout.2:" + todo.toString());
    }

}
