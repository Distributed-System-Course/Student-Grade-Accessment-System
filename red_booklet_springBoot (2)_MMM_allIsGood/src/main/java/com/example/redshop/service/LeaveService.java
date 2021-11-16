package com.example.redshop.service;

import com.example.redshop.domain.Leave;
import com.example.redshop.util.PageBean;

import java.util.Map;

public interface LeaveService {
    PageBean<Leave> queryPage(Map<String, Object> paramMap);

    int addLeave(Leave leave);

    int editLeave(Leave leave);

    int checkLeave(Leave leave);

    int deleteLeave(Integer id);
}
