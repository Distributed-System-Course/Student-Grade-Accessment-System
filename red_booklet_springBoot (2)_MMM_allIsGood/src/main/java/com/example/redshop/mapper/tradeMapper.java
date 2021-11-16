package com.example.redshop.mapper;

import com.example.redshop.domain.tradeStats;
import com.example.redshop.domain.trade;
import com.example.redshop.domain.tradeStats2;
import com.example.redshop.domain.tradeStats3;

import java.util.List;
import java.util.Map;


public interface tradeMapper {
    List<trade> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int addtrade(trade trade);

    trade istrade(trade trade);

    int edittrade(trade trade);

    int deletetrade(Integer id);

    List<trade> getAll(trade trade);

    tradeStats getAvgStats(Integer productid);

    tradeStats2 getAvgStats2(Integer sellerid);

    tradeStats3 getAvgStats3(Integer buyerid);
}
