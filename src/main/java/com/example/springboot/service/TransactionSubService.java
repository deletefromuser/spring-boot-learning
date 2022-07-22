package com.example.springboot.service;

import org.jeasy.random.EasyRandom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.dao.mapper.UserMapper;
import com.example.springboot.dao.model.User;

@Service
public class TransactionSubService {
    UserMapper mapper;
    EasyRandom easyRandom = new EasyRandom();

    @Transactional
    public void requiredSub() {
        User person = easyRandom.nextObject(User.class);
        person.setUsername("jack");
        person.setProvider("requiredSub");
        mapper.insert(person);

        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nestedSub() {
        User person = easyRandom.nextObject(User.class);
        person.setUsername("jack");
        person.setProvider("requiredSub");
        mapper.insert(person);

        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void newSub() {
        User person = easyRandom.nextObject(User.class);
        person.setUsername("jack");
        person.setProvider("requiredSub");
        mapper.insert(person);

        throw new RuntimeException();
    }
}
