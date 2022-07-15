package com.example.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.springboot.model.Todo;
import com.google.api.client.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ActiveProfiles("dev")
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbit;

    // TODO wait for spring-amqp 3.0.0-M4
    // @Autowired
    // private RabbitStreamTemplate rabbitStream;

    @Test
    public void testSendTodo() {
        EasyRandom easyRandom = new EasyRandom();
        Todo todo = easyRandom.nextObject(Todo.class);
        MessageProperties props = new MessageProperties();
        props.setHeader("REFER_TYPE", "WEB");
        Message msg = rabbit.getMessageConverter().toMessage(todo, props);
        log.info(msg.toString());
        rabbit.send(msg);

        todo = easyRandom.nextObject(Todo.class);
        rabbit.convertAndSend(todo);

        Message result = rabbit.receive("spring-boot");
        assertNotNull(result);
        assertNotNull(rabbit.getMessageConverter().fromMessage(result));

        Todo resultTodo = (Todo) rabbit.receiveAndConvert("spring-boot");
        assertNotNull(resultTodo);

        log.info(rabbit.getMessageConverter().fromMessage(result).toString());
        log.info(resultTodo.toString());

        log.info(result.toString());
        log.info(resultTodo.toString());
    }

    @Test
    public void testSendTodoByDirectExchange() {
        EasyRandom easyRandom = new EasyRandom();
        Todo todo = easyRandom.nextObject(Todo.class);
        MessageProperties props = new MessageProperties();
        props.setHeader("REFER_TYPE", "WEB");
        Message msg = rabbit.getMessageConverter().toMessage(todo, props);
        log.info(msg.toString());
        rabbit.send("direct.todos", "handler.todo", msg);

        todo = easyRandom.nextObject(Todo.class);
        rabbit.convertAndSend("direct.todos", "handler.todo", todo);

        Message result = rabbit.receive("spring-boot-direct");
        assertNotNull(result); // if @RabbitListener enabled, cannot get message here because it already be
                               // consumed
        assertNotNull(rabbit.getMessageConverter().fromMessage(result));

        Todo resultTodo = (Todo) rabbit.receiveAndConvert("spring-boot-direct");
        assertNotNull(resultTodo);

        log.info(rabbit.getMessageConverter().fromMessage(result).toString());
        log.info(resultTodo.toString());
    }

    @Test
    public void testSendTodoForListener() {
        EasyRandom easyRandom = new EasyRandom();
        Todo todo = easyRandom.nextObject(Todo.class);
        rabbit.convertAndSend(todo);
    }

}
