package com.example.springboot.service;

import org.jeasy.random.EasyRandom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.dao.mapper.UserMapper;
import com.example.springboot.dao.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {

    UserMapper mapper;

    TransactionSubService subService;

    EasyRandom easyRandom = new EasyRandom();

    public TransactionService(UserMapper mapper, TransactionSubService subService) {
        this.mapper = mapper;
        this.subService = subService;
    }

    @Transactional
    public void start(int propagation) {
        User person = easyRandom.nextObject(User.class);
        person.setUsername("tom");
        person.setProvider("tx");
        mapper.insert(person);

        try {
            switch (propagation) {
                case 0:
                    subService.requiredSub();
                    break;
                case 10:
                    requiredSub();
                    break;
                case 3:
                    subService.newSub();
                    break;
                case 6:
                    subService.nestedSub();
                    break;
                case 16:
                    nestedSub();
                    break;
            }
        } catch (Exception ignore) {
            log.info(ignore.getClass().toString());
        }

    }

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
}
