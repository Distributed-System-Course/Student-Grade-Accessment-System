package com.example.studentaccessment.serviceImpl;
import com.example.studentaccessment.bean.DataBean;
import com.example.studentaccessment.mapper.DataMapper;
import com.example.studentaccessment.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    private final DataMapper datamapper;//注入DataMapper

    public DataServiceImpl(DataMapper datamapper) {
        this.datamapper = datamapper;
    }

    //    查询所有用户
    public List<DataBean> dataList(){
        return datamapper.dataList();
    }

    //    根据姓名查找数据
    public DataBean findDataByName(String name){
        return datamapper.findDataByName(name);
    }

    //    根据项目名查找数据
    public DataBean findDataByProject(String project){
        return datamapper.findDataByProject(project);
    }
}
