package com.example.crud.service;

import com.example.crud.entity.CommitEvent;
import com.example.crud.mapper.CommitEventMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitEventService {
    @Autowired
    CommitEventMapper commitEventMapper;//mapper注入
    public List<CommitEvent> clist(){
        return commitEventMapper.clist();
    }
    /*
     * 查询所有事件
     * */
    public List<CommitEvent> committeemen(){
        return commitEventMapper.committeemen();
    }
    /*
     * 添加事件
     * */
    public void save(CommitEvent event){
        addevent(event.getPid(),event.getCommitDate(),event.getProject(),
                event.getAddLines(),event.getDeleteLines(),event.getChangeLines());
    }

    public void addevent(
            @Param("pid") int pid
            ,@Param("commitDate") String commitDate
            ,@Param("project") String project
            ,@Param("addLines") int addLines
            ,@Param("deleteLines") int deleteLines
            ,@Param("changeLines") int changeLines){
        commitEventMapper.addevent(
                pid,commitDate,project,addLines,deleteLines,changeLines
        );
    }
    /*
     * 根据date,id查询用户某天的提交
     * */
    public CommitEvent findEventByIdAndDate(
            @Param("pid") String pid
            ,@Param("commitDate") String commitDate){
        return commitEventMapper.findEventByIdAndDate(
                pid,commitDate
        );
    }
    public void updateStudent(){
        commitEventMapper.updateStudent();
    }
    public List<CommitEvent> findCommitByProject(String project){
        return commitEventMapper.findCommitByProject(project);
    }
}
