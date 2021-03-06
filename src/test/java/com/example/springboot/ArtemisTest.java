package com.example.springboot;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ActiveProfiles("dev")
public class ArtemisTest {

    // @Autowired
    // private JmsTemplate jms;

    // @Test
    // public void testSendTodo() throws MessageConversionException, JMSException {
    //     EasyRandom easyRandom = new EasyRandom();
    //     Todo todo = easyRandom.nextObject(Todo.class);
    //     jms.send("test", session -> {
    //         Message msg = session.createObjectMessage(todo);
    //         log.info(msg.toString());
    //         return msg;
    //     });

    //     Message msg = jms.receive("test");
    //     log.info(msg.toString());
    //     log.info(msg.getBody(Todo.class).toString());
    // }

    // @Test
    // public void testSendTodoByConverter() throws MessageConversionException, JMSException {
    //     EasyRandom easyRandom = new EasyRandom();
    //     Todo todo = easyRandom.nextObject(Todo.class);
    //     jms.convertAndSend(todo, msg -> {
    //         msg.setStringProperty("_typeId", "todo");
    //         return msg;
    //     });

    //     Todo result = (Todo) jms.receiveAndConvert();
    //     log.info(result.toString());
    //     assertNotNull(result);
    //     assertNotNull(result.getTitle());
    // }

    // @Test
    // public void testSendTodoForListener() throws MessageConversionException, JMSException {
    //     EasyRandom easyRandom = new EasyRandom();
    //     Todo todo = easyRandom.nextObject(Todo.class);
    //     jms.convertAndSend(todo, msg -> {
    //         msg.setStringProperty("_typeId", "todo");
    //         return msg;
    //     });
    // }

}
