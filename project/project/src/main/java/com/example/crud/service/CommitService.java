package com.example.crud.service;

import com.example.crud.entity.Commit;
import com.example.crud.mapper.CommitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitService {
    @Autowired
    CommitMapper commitMapper;
    public Commit findall(){
        return commitMapper.findall();
    }

    public List<Commit> clist(){
        return commitMapper.clist();
    }

    public Commit findCommitByProject(String project){
        return commitMapper.findCommitByProject(project);
    }

}
