package com.example.redshop.service;

import com.example.redshop.domain.Selectedproduct;
import com.example.redshop.util.PageBean;

import java.util.List;
import java.util.Map;

public interface SelectedproductService {
    PageBean<Selectedproduct> queryPage(Map<String, Object> paramMap);

    int addSelectedproduct(Selectedproduct selectedproduct);

    int deleteSelectedproduct(Integer id);

    boolean isbuyerId(int id);

    List<Selectedproduct> getAllBySid(int buyerid);
}
