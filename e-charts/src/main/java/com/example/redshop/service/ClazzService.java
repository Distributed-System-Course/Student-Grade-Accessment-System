package com.example.redshop.service;

import com.example.redshop.domain.Clazz;
import com.example.redshop.util.PageBean;

import java.util.List;
import java.util.Map;


public interface ClazzService {
    PageBean<Clazz> queryPage(Map<String, Object> paramMap);

    int addClazz(Clazz clazz);

    int deleteClazz(List<Integer> ids);

    int editClazz(Clazz clazz);

    Clazz findByName(String clazzName);

}
