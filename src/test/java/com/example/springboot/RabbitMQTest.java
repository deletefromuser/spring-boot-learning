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

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ActiveProfiles("dev")
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate jms;

    @Test
    public void testSendTodo() {
        EasyRandom easyRandom = new EasyRandom();
        Todo todo = easyRandom.nextObject(Todo.class);
        MessageProperties props = new MessageProperties();
        props.setHeader("REFER_TYPE", "WEB");
        Message msg = jms.getMessageConverter().toMessage(todo, props);
        log.info(msg.toString());
        jms.send(msg);

        todo = easyRandom.nextObject(Todo.class);
        jms.convertAndSend(todo);

        Message result = jms.receive("spring-boot");
        assertNotNull(result);
        assertNotNull(jms.getMessageConverter().fromMessage(result));

        Todo resultTodo = (Todo) jms.receiveAndConvert("spring-boot");
        assertNotNull(resultTodo);

        log.info(jms.getMessageConverter().fromMessage(result).toString());
        log.info(resultTodo.toString());
    }

    // @Test
    // public void testSendTodoByConverter() throws MessageConversionException,
    // JMSException {
    // EasyRandom easyRandom = new EasyRandom();
    // Todo todo = easyRandom.nextObject(Todo.class);
    // jms.convertAndSend(todo, msg -> {
    // msg.setStringProperty("_typeId", "todo");
    // return msg;
    // });

    // Todo result = (Todo) jms.receiveAndConvert();
    // log.info(result.toString());
    // assertNotNull(result);
    // assertNotNull(result.getTitle());
    // }

    // @Test
    // public void testSendTodoForListener() throws MessageConversionException,
    // JMSException {
    // EasyRandom easyRandom = new EasyRandom();
    // Todo todo = easyRandom.nextObject(Todo.class);
    // jms.convertAndSend(todo, msg -> {
    // msg.setStringProperty("_typeId", "todo");
    // return msg;
    // });
    // }

}
