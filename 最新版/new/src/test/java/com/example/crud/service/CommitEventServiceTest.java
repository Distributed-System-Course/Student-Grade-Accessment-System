package com.example.crud.service;
import com.example.crud.entity.CommitEvent;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class CommitEventServiceTest {
    @Autowired
    CommitEventService commitEventService;

    @Test
    void committeemen() {
        List<CommitEvent> list= commitEventService.committeemen();
        for (CommitEvent event: list)
            System.out.println(event);
    }

    @Test
    void save() {

    }

    @Test
    void addevent() {
    }

    @Test
    void findEventByIdAndDate() {
    }

    @Test
    void updateStudent() {
    }
}