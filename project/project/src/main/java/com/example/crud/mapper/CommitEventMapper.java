package com.example.crud.mapper;

import com.example.crud.entity.CommitEvent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitEventMapper {
    CommitEvent findall();

    List<CommitEvent> clist();

    List<CommitEvent> findCommitByProject(String project);

}
