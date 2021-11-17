package com.example.redshop.mapper;

import com.example.redshop.domain.product;

import java.util.List;
import java.util.Map;


public interface productMapper {
    List<product> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int addproduct(product product);

    int editproduct(product product);

    int deleteproduct(List<Integer> ids);

    int addbuyerNum(Integer productId);

    void deletebuyerNum(Integer productId);

    List<product> getproductById(List<Integer> ids);

    int findByName(String name);
}
