package com.example.crud.mapper;

import com.example.crud.entity.CommitEvent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitEventMapper {
    /*
    * 查询所有事件
    * */
    List<CommitEvent> committeemen();
    /*
    * 添加事件
    * */
    void save(CommitEvent event);

    void addevent(@Param("pid") String pid
            ,@Param("pname") String pname
            ,@Param("commitDate") String commitDate
            ,@Param("totalCommit") String totalCommit);
    /*
    * 根据date,id查询用户某天的提交
    * */
    CommitEvent findEventByIdAndDate(
            @Param("pid") String pid
            ,@Param("date") String date);

}
