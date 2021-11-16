package com.example.redshop.service.Impl;

import com.example.redshop.domain.buyer;
import com.example.redshop.mapper.buyerMapper;
import com.example.redshop.service.buyerService;
import com.example.redshop.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class buyerServiceImpl implements buyerService {

    @Autowired
    private buyerMapper buyerMapper;

    @Override
    public PageBean<buyer> queryPage(Map<String, Object> paramMap) {
        PageBean<buyer> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);

        System.out.println("buyer-----------------------");
        System.out.println("遍历paraMap");
        for (String key : paramMap.keySet()) {
            System.out.println("Key = " + key);
        }
        System.out.println("遍历paraMap_values");
        for (Object value : paramMap.values()) {
            System.out.println("Value = " + value);
        }
        System.out.println("buyer-----------------------");

        List<buyer> datas = buyerMapper.queryList(paramMap);
        for(buyer str : datas) {//其内部实质上还是调用了迭代器遍历方式，这种循环方式还有其他限制，不建议使用。
            System.out.println(str.getId());
        }
        System.out.println("buyerMapper-----------------------");
        pageBean.setDatas(datas);

        Integer totalsize = buyerMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int deletebuyer(List<Integer> ids) {
        return buyerMapper.deletebuyer(ids);
    }

    @Override
    public int addbuyer(buyer buyer) {
        return buyerMapper.addbuyer(buyer);
    }

    @Override
    public buyer findById(Integer sid) {
        return buyerMapper.findById(sid);
    }

    @Override
    public int editbuyer(buyer buyer) {
        return buyerMapper.editbuyer(buyer);
    }

    @Override
    public buyer findBybuyer(buyer buyer) {
        return buyerMapper.findBybuyer(buyer);
    }

    @Override
    public boolean isbuyerByClazzId(Integer id) {
        List<buyer> buyerList = buyerMapper.isbuyerByClazzId(id);
        if (buyerList.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int editPswdBybuyer(buyer buyer) {
        return buyerMapper.editPswdBybuyer(buyer);
    }

    @Override
    public int findByName(String name) {
        return buyerMapper.findByName(name);
    }
}
