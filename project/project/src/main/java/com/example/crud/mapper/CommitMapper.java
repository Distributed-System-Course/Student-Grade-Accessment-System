package com.example.crud.mapper;

import com.example.crud.entity.Commit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitMapper {
    Commit findall();

    List<Commit> clist();

    Commit findCommitByProject(String project);

}
