package com.example.redshop.service;

import com.example.redshop.domain.product;
import com.example.redshop.util.PageBean;

import java.util.List;
import java.util.Map;


public interface productService {
    PageBean<product> queryPage(Map<String, Object> paramMap);

    int addproduct(product product);

    int editproduct(product product);

    int deleteproduct(List<Integer> ids);

    List<product> getproductById(List<Integer> ids);

    int findByName(String name);
}
