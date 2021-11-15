package com.example.crud.service;

import com.example.crud.entity.Project;
import com.example.crud.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdjectService {
    @Autowired
    ProjectMapper projectMapper;
    public Project findall() {
        return projectMapper.findall();
    }

    public List<Project> pList() {
        return  projectMapper.plist();
    }

    public Project findProjectById(int pid) {
        return projectMapper.findProjectById(pid);
    }
}
