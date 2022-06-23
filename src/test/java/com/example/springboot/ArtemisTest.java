package com.example.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.jms.JMSException;
import javax.jms.Message;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.example.springboot.model.Todo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ArtemisTest {

    @Autowired
    private JmsTemplate jms;

    // @Autowired
    // private MappingJackson2MessageConverter converter;

    @Test
    public void testSendTodo() throws MessageConversionException, JMSException {
        EasyRandom easyRandom = new EasyRandom();
        Todo todo = easyRandom.nextObject(Todo.class);
        todo.set_typeId("todo");
        jms.send(session -> {
            Message msg = session.createObjectMessage(todo);
            // converter.setTypeIdPropertyName(typeIdPropertyName);
            // msg.setStringProperty("_typeId", "todo");
            log.info(msg.toString());
            return msg;
        });

        // Message msg = jms.receive();
        // Todo result = (Todo) converter.fromMessage(msg);
        Todo result = (Todo) jms.receiveAndConvert();
        log.info(result.toString());
        assertNotNull(result);
        assertNotNull(result.getTitle());
    }

}
