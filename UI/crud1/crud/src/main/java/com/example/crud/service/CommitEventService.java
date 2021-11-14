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
    /*
     * 查询所有事件
     * */
    public List<CommitEvent> committeemen(){
        return commitEventMapper.committeemen();
    }
    /*
     * 添加事件
     * */
    void save(CommitEvent event){
        commitEventMapper.save(event);
    }

    void addevent(@Param("pid") String pid
            ,@Param("pname") String pname
            ,@Param("commitDate") String commitDate
            ,@Param("totalCommit") String totalCommit){
        commitEventMapper.addevent(
                pid,pname,commitDate,totalCommit
        );
    }
    /*
     * 根据date,id查询用户某天的提交
     * */
    CommitEvent findEventByIdAndDate(
            @Param("pid") String pid
            ,@Param("date") String date){
        return commitEventMapper.findEventByIdAndDate(
          pid,date
        );
    }
}
