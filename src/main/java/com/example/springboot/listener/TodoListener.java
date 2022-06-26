package com.example.springboot.listener;

import java.util.UUID;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.springboot.model.Todo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TodoListener {

    // https://stackoverflow.com/a/39904299/19120213
    // @JmsListener(destination = "tacocloud.order.queue")
    // @RabbitListener(queues = "spring-boot")
    // @KafkaListener(topics = "topic.todo", groupId = "test")
    // public void receiveOrder1(byte[] todoBytes, ConsumerRecord<String, byte[]> consumerRecord) {
    //     log.info("consumer 1: " + SerializationUtils.deserialize(todoBytes).toString());
    //     log.info("consumer 1: " + consumerRecord.headers().toString());
    //     log.info("consumer 1: " + SerializationUtils.deserialize(consumerRecord.value()).toString());
    // }

    // @KafkaListener(topics = "topic.todo", groupId = "test")
    // public void receiveOrder2(byte[] todoBytes, ConsumerRecord<String, byte[]> consumerRecord) {
    //     log.info("consumer 2: " + SerializationUtils.deserialize(todoBytes).toString());
    //     log.info("consumer 2: " + consumerRecord.headers().toString());
    //     log.info("consumer 2: " + SerializationUtils.deserialize(consumerRecord.value()).toString());
    // }

    // @KafkaListener(topics = "topic.todo", groupId = "test2")
    // public void receiveOrderForBroadcast(byte[] todoBytes, ConsumerRecord<String, byte[]> consumerRecord) {
    //     log.info("broadcast: " + SerializationUtils.deserialize(todoBytes).toString());
    //     log.info("broadcast: " + consumerRecord.headers().toString());
    //     log.info("broadcast: " + SerializationUtils.deserialize(consumerRecord.value()).toString());
    // }

    @KafkaListener(topics = "topic.todo.json", groupId = "test")
    public void receiveOrder1(Todo todo, ConsumerRecord<String, Todo> consumerRecord) {
        log.info("consumer 1: " + todo.toString());
        log.info("consumer 1: " + consumerRecord.headers().toString());
        log.info("consumer 1: " + consumerRecord.value().toString());
    }

}
