package com.example.redshop.service.Impl;

import com.example.redshop.domain.*;
import com.example.redshop.mapper.tradeMapper;
import com.example.redshop.service.tradeService;
import com.example.redshop.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class tradeServiceImpl implements tradeService {

    @Autowired
    private tradeMapper tradeMapper;

    @Override
    public PageBean<trade> queryPage(Map<String, Object> paramMap) {
        PageBean<trade> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);

        System.out.println("trade-----------------------");
        System.out.println("遍历paraMap");
        for (String key : paramMap.keySet()) {
            System.out.println("Key = " + key);
        }
        System.out.println("遍历paraMap_values");
        for (Object value : paramMap.values()) {
            System.out.println("Value = " + value);
        }
        System.out.println("trade-----------------------");

        List<trade> datas = tradeMapper.queryList(paramMap);
        System.out.println("service*****************");
        System.out.println(datas.size());
        System.out.println("service*****************");
        for(trade str : datas) {//其内部实质上还是调用了迭代器遍历方式，这种循环方式还有其他限制，不建议使用。
            System.out.println(str.getId());
        }
        System.out.println("tradeMapper-----------------------");

        pageBean.setDatas(datas);

        Integer totalsize = tradeMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public boolean istrade(trade trade) {
        trade sc = tradeMapper.istrade(trade);
        if(sc != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int addtrade(trade trade)
    {

        return tradeMapper.addtrade(trade);
    }

    @Override
    public int edittrade(trade trade) {
        return tradeMapper.edittrade(trade);
    }

    @Override
    public int deletetrade(Integer id) {
        return tradeMapper.deletetrade(id);
    }

    @Override
    public List<trade> getAll(trade trade) {
        return tradeMapper.getAll(trade);
    }

    @Override
    public tradeStats getAvgStats(Integer productid) {
        return tradeMapper.getAvgStats(productid);
    }

    @Override
    public tradeStats2 getAvgStats2(Integer sellerid) {
        return tradeMapper.getAvgStats2(sellerid);
    }

    @Override
    public tradeStats3 getAvgStats3(Integer buyerid) {
        return tradeMapper.getAvgStats3(buyerid);
    }
}
