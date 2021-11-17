package com.example.redshop.service;

import com.example.redshop.domain.Attendance;
import com.example.redshop.util.PageBean;

import java.util.Map;


public interface AttendanceService {
    PageBean<Attendance> queryPage(Map<String, Object> paramMap);

    boolean isAttendance(Attendance attendance);

    int addAtendance(Attendance attendance);

    int deleteAttendance(Integer id);
}
