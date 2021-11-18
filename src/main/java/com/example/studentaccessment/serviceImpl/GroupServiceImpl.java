package com.example.studentaccessment.serviceImpl;

import com.example.studentaccessment.bean.GroupBean;
import com.example.studentaccessment.mapper.GroupMapper;
import com.example.studentaccessment.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupMapper groupMapper;

    public GroupBean select(int groupID)
    {
        return groupMapper.getInfo(groupID);
    }
}
