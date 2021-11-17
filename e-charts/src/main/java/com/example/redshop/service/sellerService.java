package com.example.redshop.service;

import com.example.redshop.domain.seller;
import com.example.redshop.util.PageBean;

import java.util.List;
import java.util.Map;


public interface sellerService {
    PageBean<seller> queryPage(Map<String, Object> paramMap);

    int deleteseller(List<Integer> ids);

    int addseller(seller seller);

    seller findById(Integer tid);

    int editseller(seller seller);

    seller findByseller(seller seller);

    int editPswdByseller(seller seller);

    int findByName(String stringCellValue);


}
