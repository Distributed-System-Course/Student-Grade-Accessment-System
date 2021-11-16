package com.example.redshop.mapper;

import com.example.redshop.domain.seller;

import java.util.List;
import java.util.Map;


public interface sellerMapper {
    List<seller> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int deleteseller(List<Integer> ids);

    int addseller(seller seller);

    seller findById(Integer tid);

    int editseller(seller seller);

    seller findByseller(seller seller);

    int editPswdByseller(seller seller);

    int findByName(String name);
}
