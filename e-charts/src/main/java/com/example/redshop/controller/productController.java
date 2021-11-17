package com.example.redshop.controller;

import com.example.redshop.domain.product;
import com.example.redshop.service.productService;
import com.example.redshop.util.AjaxResult;
import com.example.redshop.util.Data;
import com.example.redshop.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class productController {

    @Autowired
    private productService productService;

    @GetMapping("/product_list")
    public String productList(){
        return "product/productList";
    }

    @PostMapping("/getproductList")
    @ResponseBody
    public Object getClazzList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                               @RequestParam(value = "rows", defaultValue = "100")Integer rows,
                               String name,
                               @RequestParam(value = "sellerid", defaultValue = "0")String sellerid ,String from){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!StringUtils.isEmpty(name))  paramMap.put("name",name);
        if(!sellerid.equals("0"))  paramMap.put("sellerId",sellerid);
        PageBean<product> pageBean = productService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }

    @PostMapping("/addproduct")
    @ResponseBody
    public AjaxResult addproduct(product product){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = productService.addproduct(product);
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


    @PostMapping("/editproduct")
    @ResponseBody
    public AjaxResult editproduct(product product){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = productService.editproduct(product);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失111");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }


    @PostMapping("/deleteproduct")
    @ResponseBody
    public AjaxResult deleteproduct(Data data){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = productService.deleteproduct(data.getIds());
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
            ajaxResult.setMessage("删除失败,该班级存在老师或学生");
        }
        return ajaxResult;
    }
}
