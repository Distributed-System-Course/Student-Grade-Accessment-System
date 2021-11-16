package com.example.redshop.service;


import com.example.redshop.domain.trade;
import com.example.redshop.domain.tradeStats;
import com.example.redshop.domain.tradeStats2;
import com.example.redshop.domain.tradeStats3;
import com.example.redshop.util.PageBean;


import java.util.List;
import java.util.Map;


public interface tradeService {
    PageBean<trade> queryPage(Map<String, Object> paramMap);

    boolean istrade(trade trade);

    int addtrade(trade trade);

    int edittrade(trade trade);

    int deletetrade(Integer id);

    List<trade> getAll(trade trade);

    tradeStats getAvgStats(Integer productid);

    tradeStats2 getAvgStats2(Integer sellerid);

    tradeStats3 getAvgStats3(Integer buyerid);
}
