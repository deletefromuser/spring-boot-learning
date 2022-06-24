package com.example.springboot;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.example.springboot.model.Todo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ActiveProfiles("dev")
public class KafkaTest {

    @Autowired
    private KafkaTemplate<String, Todo> kafka;

    @Test
    public void testSendTodo() {
        EasyRandom easyRandom = new EasyRandom();
        Todo todo = easyRandom.nextObject(Todo.class);
        kafka.send("topic.todo", todo);

        // todo = easyRandom.nextObject(Todo.class);
        // kafka.convertAndSend(todo);

        // Message result = kafka.receive("spring-boot");
        // assertNotNull(result);
        // assertNotNull(kafka.getMessageConverter().fromMessage(result));

        // Todo resultTodo = (Todo) kafka.receiveAndConvert("spring-boot");
        // assertNotNull(resultTodo);

        // log.info(kafka.getMessageConverter().fromMessage(result).toString());
        // log.info(resultTodo.toString());
    }

    // @Test
    // public void testSendTodoByDirectExchange() {
    //     EasyRandom easyRandom = new EasyRandom();
    //     Todo todo = easyRandom.nextObject(Todo.class);
    //     MessageProperties props = new MessageProperties();
    //     props.setHeader("REFER_TYPE", "WEB");
    //     Message msg = kafka.getMessageConverter().toMessage(todo, props);
    //     log.info(msg.toString());
    //     kafka.send("direct.todos", "handler.todo", msg);

    //     todo = easyRandom.nextObject(Todo.class);
    //     kafka.convertAndSend("direct.todos", "handler.todo", todo);

    //     Message result = kafka.receive("spring-boot-direct");
    //     assertNotNull(result);
    //     assertNotNull(kafka.getMessageConverter().fromMessage(result));

    //     Todo resultTodo = (Todo) kafka.receiveAndConvert("spring-boot-direct");
    //     assertNotNull(resultTodo);

    //     log.info(kafka.getMessageConverter().fromMessage(result).toString());
    //     log.info(resultTodo.toString());
    // }

    // @Test
    // public void testSendTodoForListener() {
    //     EasyRandom easyRandom = new EasyRandom();
    //     Todo todo = easyRandom.nextObject(Todo.class);
    //     kafka.convertAndSend(todo);
    // }

}
