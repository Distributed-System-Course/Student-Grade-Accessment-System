package com.example.studentaccessment.mapper;
import com.example.studentaccessment.bean.DataBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DataMapper {
//    查询所有数据
    List<DataBean> dataList();

//     根据姓名查找数据
    DataBean findDataByName(String name);

//    根据项目名查找数据
    DataBean findDataByProject(String project);
}
