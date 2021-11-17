package com.example.redshop.service.Impl;

import com.example.redshop.domain.product;
import com.example.redshop.mapper.productMapper;
import com.example.redshop.service.productService;
import com.example.redshop.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class productServiceImpl implements productService {

    @Autowired
    private productMapper productMapper;

    @Override
    public PageBean<product> queryPage(Map<String, Object> paramMap) {
        PageBean<product> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<product> datas = productMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = productMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int addproduct(product product) {
        return productMapper.addproduct(product);
    }

    @Override
    public int editproduct(product product) {
        return productMapper.editproduct(product);
    }

    @Override
    public int deleteproduct(List<Integer> ids) {
        return productMapper.deleteproduct(ids);
    }

    @Override
    public List<product> getproductById(List<Integer> ids) {
        return productMapper.getproductById(ids);
    }

    @Override
    public int findByName(String name) {
        return productMapper.findByName(name);
    }

}
