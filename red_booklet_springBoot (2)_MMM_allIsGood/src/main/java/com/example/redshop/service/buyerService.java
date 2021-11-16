package com.example.redshop.service;

import com.example.redshop.domain.buyer;
import com.example.redshop.util.PageBean;

import java.util.List;
import java.util.Map;


public interface buyerService {
    PageBean<buyer> queryPage(Map<String, Object> paramMap);

    int deletebuyer(List<Integer> ids);

    int addbuyer(buyer buyer);

    buyer findById(Integer sid);

    int editbuyer(buyer buyer);

    buyer findBybuyer(buyer buyer);

    boolean isbuyerByClazzId(Integer next);

    int editPswdBybuyer(buyer buyer);

    int findByName(String username);
}
