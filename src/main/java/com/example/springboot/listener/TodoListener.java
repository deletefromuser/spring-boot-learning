package com.example.springboot.listener;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TodoListener {

    // https://stackoverflow.com/a/39904299/19120213
    // @JmsListener(destination = "tacocloud.order.queue")
    // @RabbitListener(queues = "spring-boot")
    @KafkaListener(topics = "topic.todo", groupId = "test")
    public void receiveOrder(byte[] todoBytes, ConsumerRecord<String, byte[]> consumerRecord) {
        log.info(SerializationUtils.deserialize(todoBytes).toString());
        log.info(consumerRecord.key());
    }

}
