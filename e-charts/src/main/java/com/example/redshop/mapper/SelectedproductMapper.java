package com.example.redshop.mapper;

import com.example.redshop.domain.Selectedproduct;

import java.util.List;
import java.util.Map;

public interface SelectedproductMapper {
    List<Selectedproduct> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int addSelectedproduct(Selectedproduct selectedproduct);

    Selectedproduct findBySelectedproduct(Selectedproduct selectedproduct);

    Selectedproduct findById(Integer id);

    int deleteSelectedproduct(Integer id);

    List<Selectedproduct> isbuyerId(int id);

    List<Selectedproduct> getAllBySid(int buyerid);
}
