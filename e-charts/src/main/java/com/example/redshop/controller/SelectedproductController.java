package com.example.redshop.controller;

import com.example.redshop.domain.Selectedproduct;
import com.example.redshop.domain.buyer;
import com.example.redshop.service.SelectedproductService;
import com.example.redshop.util.AjaxResult;
import com.example.redshop.util.Const;
import com.example.redshop.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/selectedproduct")
public class SelectedproductController {

    @Autowired
    private SelectedproductService selectedproductService;



    @GetMapping("/selectedproduct_list")
    public String selectedproductList(){
        return "product/selectedproductList";
    }


    @PostMapping("/getSelectedproductList")
    @ResponseBody
    public Object getClazzList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                               @RequestParam(value = "rows", defaultValue = "100")Integer rows,
                               @RequestParam(value = "sellerid", defaultValue = "0")String buyerid,
                               @RequestParam(value = "sellerid", defaultValue = "0")String productid ,String from,HttpSession session){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!buyerid.equals("0"))  paramMap.put("buyerId",buyerid);
        if(!productid.equals("0"))  paramMap.put("productId",productid);
        //判断是卖家还是买家权限
        buyer buyer = (buyer) session.getAttribute(Const.buyer);
        if(!StringUtils.isEmpty(buyer)){
            //是买家权限，只能查询自己的信息
            paramMap.put("buyerid",buyer.getId());
        }
        PageBean<Selectedproduct> pageBean = selectedproductService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }


    @PostMapping("/addSelectedproduct")
    @ResponseBody
    public AjaxResult addSelectedproduct(Selectedproduct selectedproduct){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = selectedproductService.addSelectedproduct(selectedproduct);
            if(count == 1){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("交易成功");
            }else if(count == 0){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("余额已满");
            }else if(count == 2){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("交易成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("系统内部出错，请联系管理员!");
        }
        return ajaxResult;
    }



    @PostMapping("/deleteSelectedproduct")
    @ResponseBody
    public AjaxResult deleteSelectedproduct(Integer id){
        AjaxResult ajaxResult = new AjaxResult();

        try {
            int count = selectedproductService.deleteSelectedproduct(id);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("移除成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("移除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxResult;
    }


}
