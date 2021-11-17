package com.example.redshop.controller;

import com.example.redshop.domain.Clazz;
import com.example.redshop.service.ClazzService;
import com.example.redshop.service.buyerService;
import com.example.redshop.util.AjaxResult;
import com.example.redshop.util.Data;
import com.example.redshop.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private buyerService buyerService;

    /**
     * 跳转社区页面
     * @return
     */
    @GetMapping("/clazz_list")
    public String clazzList(){
        return "/clazz/clazzList";
    }

    /**
     * 异步加载社区列表
     * @param page
     * @param rows
     * @param clazzName
     * @return
     */
    @PostMapping("/getClazzList")
    @ResponseBody
    public Object getClazzList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                               @RequestParam(value = "rows", defaultValue = "100")Integer rows, String clazzName, String from){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!StringUtils.isEmpty(clazzName))  paramMap.put("name",clazzName);
        PageBean<Clazz> pageBean = clazzService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }


    @PostMapping("/addClazz")
    @ResponseBody
    public AjaxResult addClazz(Clazz clazz){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = clazzService.addClazz(clazz);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("添加成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }

    @PostMapping("/deleteClazz")
    @ResponseBody
    public AjaxResult deleteClazz(Data data){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            List<Integer> ids = data.getIds();
            Iterator<Integer> iterator = ids.iterator();
            while (iterator.hasNext()){  //判断是否存在课程关联学生
                if(!buyerService.isbuyerByClazzId(iterator.next())){
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("无法删除,社区下存在买家");
                    return ajaxResult;
                }
            }
            int count = clazzService.deleteClazz(data.getIds());
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("删除成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("删除失败,该班级存在卖家或买家");
        }
        return ajaxResult;
    }

    @PostMapping("/editClazz")
    @ResponseBody
    public AjaxResult editClazz(Clazz clazz){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = clazzService.editClazz(clazz);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }
}
