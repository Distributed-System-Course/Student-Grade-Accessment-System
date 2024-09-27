package com.example.crud.mapper;

import com.example.crud.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper {
    Project findall();

    List<Project> plist();

    Project findProjectById(int pid);
}
