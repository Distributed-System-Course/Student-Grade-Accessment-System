package com.example.redshop.mapper;

import com.example.redshop.domain.buyer;

import java.util.List;
import java.util.Map;


public interface buyerMapper {
    List<buyer> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int deletebuyer(List<Integer> ids);

    int addbuyer(buyer buyer);

    buyer findById(Integer sid);

    int editbuyer(buyer buyer);

    buyer findBybuyer(buyer buyer);

    List<buyer> isbuyerByClazzId(Integer id);

    int editPswdBybuyer(buyer buyer);

    int findByName(String name);
}
