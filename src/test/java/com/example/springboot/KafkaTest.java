package com.example.springboot;

import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.springboot.model.Todo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ActiveProfiles("dev")
public class KafkaTest {

    // @Autowired
    // private KafkaTemplate<String, String> kafka;

    // @Autowired
    // private KafkaTemplate<String, byte[]> kafkaObject;

    // @Autowired
    // private KafkaTemplate<String, Todo> kafkaJson;

    // // https://stackoverflow.com/a/37056589/19120213
    // @Test
    // public void testSendTodo() {
    //     EasyRandom easyRandom = new EasyRandom();
    //     Todo todo = easyRandom.nextObject(Todo.class);
    //     todo.setTitle(RandomStringUtils.randomAlphanumeric(20));
    //     // kafka.send("topic.todo", "hello kafka");

    //     // kafkaObject.sendDefault(SerializationUtils.serialize(todo));
    //     ListenableFuture<SendResult<String, Todo>> future = kafkaJson.send("topic.todo.json", todo);
    //     future.addCallback(result -> {
    //         log.info("send success");
    //         log.info(result.getProducerRecord().toString());
    //         log.info(result.getRecordMetadata().toString());
    //     }, ex -> log.error("send error", ex));

    //     todo = easyRandom.nextObject(Todo.class);
    //     todo.setTitle(RandomStringUtils.randomAlphanumeric(20));

    //     kafkaJson.send("topic.todo.json", todo);
    // }

}
