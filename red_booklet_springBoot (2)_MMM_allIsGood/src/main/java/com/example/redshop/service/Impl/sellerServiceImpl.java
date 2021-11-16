package com.example.redshop.service.Impl;

import com.example.redshop.domain.seller;
import com.example.redshop.mapper.sellerMapper;
import com.example.redshop.service.sellerService;
import com.example.redshop.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class sellerServiceImpl implements sellerService {

    @Autowired
    private sellerMapper sellerMapper;

    @Override
    public PageBean<seller> queryPage(Map<String, Object> paramMap) {
        PageBean<seller> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<seller> datas = sellerMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = sellerMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int deleteseller(List<Integer> ids) {
        return sellerMapper.deleteseller(ids);
    }

    @Override
    public int addseller(seller seller) {
        return sellerMapper.addseller(seller);
    }

    @Override
    public seller findById(Integer tid) {
        return sellerMapper.findById(tid);
    }

    @Override
    public int editseller(seller seller) {
        return sellerMapper.editseller(seller);
    }

    @Override
    public seller findByseller(seller seller) {
        return sellerMapper.findByseller(seller);
    }

    @Override
    public int findByName(String name) {
        return sellerMapper.findByName(name);
    }

    @Override
    public int editPswdByseller(seller seller) {
        return sellerMapper.editPswdByseller(seller);
    }
}
