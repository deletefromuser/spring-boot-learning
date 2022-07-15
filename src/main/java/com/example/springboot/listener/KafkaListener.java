package com.example.springboot.listener;

// import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import com.example.springboot.model.Todo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaListener {

    // // https://stackoverflow.com/a/39904299/19120213
    // @org.springframework.kafka.annotation.KafkaListener(topics = "topic.todo.json", groupId = "test")
    // public void receiveOrder2(Todo todo, ConsumerRecord<String, Todo> consumerRecord) {
    //     log.info("consumer 2: " + todo.toString());
    //     log.info("consumer 2: " + consumerRecord.headers().toString());
    //     log.info("consumer 2: " + consumerRecord.value().toString());
    // }

    // @org.springframework.kafka.annotation.KafkaListener(topics = "topic.todo.json", groupId = "test2")
    // public void receiveOrderForBroadcast(Todo todo, ConsumerRecord<String, Todo> consumerRecord) {
    //     log.info("broadcast: " + todo.toString());
    //     log.info("broadcast: " + consumerRecord.headers().toString());
    //     log.info("broadcast: " + consumerRecord.value().toString());
    // }

    // @org.springframework.kafka.annotation.KafkaListener(topics = "topic.todo.json", groupId = "test")
    // public void receiveOrder1(Todo todo, ConsumerRecord<String, Todo> consumerRecord) {
    //     log.info("consumer 1: " + todo.toString());
    //     log.info("consumer 1: " + consumerRecord.headers().toString());
    //     log.info("consumer 1: " + consumerRecord.value().toString());
    // }

}
