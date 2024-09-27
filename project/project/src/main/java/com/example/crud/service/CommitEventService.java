package com.example.crud.service;

import com.example.crud.entity.CommitEvent;
import com.example.crud.mapper.CommitEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitEventService {
    @Autowired
    CommitEventMapper commitEventMapper;
    public CommitEvent findall(){
        return commitEventMapper.findall();
    }

    public List<CommitEvent> clist(){
        return commitEventMapper.clist();
    }

    public List<CommitEvent> findCommitByProject(String project){
        return commitEventMapper.findCommitByProject(project);
    }


}
