package com.example.studentaccessment.service;
import com.example.studentaccessment.bean.DataBean;
import java.util.List;

public interface DataService {
    public List<DataBean> dataList();
    public DataBean findDataByName(String name);
    public DataBean findDataByProject(String project);
}
