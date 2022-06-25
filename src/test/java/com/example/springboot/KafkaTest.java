package com.example.springboot;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.SerializationUtils;

import com.example.springboot.model.Todo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ActiveProfiles("dev")
public class KafkaTest {

    @Autowired
    private KafkaTemplate<String, String> kafka;

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaObject;

    // https://stackoverflow.com/a/37056589/19120213
    @Test
    public void testSendTodo() {
        EasyRandom easyRandom = new EasyRandom();
        Todo todo = easyRandom.nextObject(Todo.class);
        // kafka.send("topic.todo", "hello kafka");

        kafkaObject.sendDefault(SerializationUtils.serialize(todo));
    }

}
