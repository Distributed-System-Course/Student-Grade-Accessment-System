package com.example.redshop.service.Impl;

import com.example.redshop.domain.Selectedproduct;
import com.example.redshop.mapper.SelectedproductMapper;
import com.example.redshop.mapper.productMapper;
import com.example.redshop.service.SelectedproductService;
import com.example.redshop.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service
public class SelectedproductServiceImpl implements SelectedproductService {

    @Autowired
    private SelectedproductMapper selectedproductMapper;
    @Autowired
    private productMapper productMapper;

    @Override
    public PageBean<Selectedproduct> queryPage(Map<String, Object> paramMap) {
        PageBean<Selectedproduct> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Selectedproduct> datas = selectedproductMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = selectedproductMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    @Transactional
    public int addSelectedproduct(Selectedproduct selectedproduct) {
        Selectedproduct s = selectedproductMapper.findBySelectedproduct(selectedproduct);
        if(StringUtils.isEmpty(s)){
            int count = productMapper.addbuyerNum(selectedproduct.getproductId());
            if(count == 1){
                selectedproductMapper.addSelectedproduct(selectedproduct);
                return count;
            }
            if(count == 0){
                return count;
            }
        }else{
            return 2;
        }
        return 3;
    }

    @Override
    @Transactional
    public int deleteSelectedproduct(Integer id) {
        Selectedproduct selectedproduct = selectedproductMapper.findById(id);
        productMapper.deletebuyerNum(selectedproduct.getproductId());
        return selectedproductMapper.deleteSelectedproduct(id);
    }

    @Override
    public boolean isbuyerId(int id) {
        List<Selectedproduct> selectedproductList = selectedproductMapper.isbuyerId(id);
        if (selectedproductList.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Selectedproduct> getAllBySid(int buyerid) {
        return selectedproductMapper.getAllBySid(buyerid);
    }
}
