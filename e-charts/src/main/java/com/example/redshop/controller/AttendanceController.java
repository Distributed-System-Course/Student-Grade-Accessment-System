package com.example.redshop.controller;

import com.example.redshop.domain.Attendance;
import com.example.redshop.domain.product;
import com.example.redshop.domain.Selectedproduct;
import com.example.redshop.domain.buyer;
import com.example.redshop.service.AttendanceService;
import com.example.redshop.service.productService;
import com.example.redshop.service.SelectedproductService;
import com.example.redshop.util.AjaxResult;
import com.example.redshop.util.DateFormatUtil;
import com.example.redshop.util.PageBean;
import com.example.redshop.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private SelectedproductService selectedproductService;
    @Autowired
    private productService productService;

    @GetMapping("/attendance_list")
    public String attendanceList(){
        return "/attendance/attendanceList";
    }


    @RequestMapping("/getAttendanceList")
    @ResponseBody
    public Object getAttendanceList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100")Integer rows,
                                 @RequestParam(value = "buyerid", defaultValue = "0")String buyerid,
                                 @RequestParam(value = "productid", defaultValue = "0")String productid,
                                 String type,String date, String from, HttpSession session){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!buyerid.equals("0"))  paramMap.put("buyerid",buyerid);
        if(!productid.equals("0"))  paramMap.put("productid",productid);
        if(!StringUtils.isEmpty(type))  paramMap.put("type",type);
        if(!StringUtils.isEmpty(date))  paramMap.put("date",date);

        //?????????????????????????????????
        buyer buyer = (buyer) session.getAttribute(Const.buyer);
        if(!StringUtils.isEmpty(buyer)){
            //?????????????????????????????????????????????
            paramMap.put("buyerid",buyer.getId());
        }
        PageBean<Attendance> pageBean = attendanceService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }

    @RequestMapping("/getbuyerSelectedproductList")
    @ResponseBody
    public Object getbuyerSelectedproductList(@RequestParam(value = "buyerid", defaultValue = "0")String buyerid){
        //????????????id ?????? ????????????
        List<Selectedproduct> selectedproductList = selectedproductService.getAllBySid(Integer.parseInt(buyerid));
        //?????? ????????????????????????id ?????? ????????????????????????
        List<Integer> ids = new ArrayList<>();
        for(Selectedproduct selectedproduct : selectedproductList){
            ids.add(selectedproduct.getproductId());
        }
        List<product> productList = productService.getproductById(ids);
        return productList;
    }

    @PostMapping("/addAttendance")
    @ResponseBody
    public AjaxResult addAttendance(Attendance attendance){
        AjaxResult ajaxResult = new AjaxResult();
        attendance.setDate(DateFormatUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        //?????????????????????
        if(attendanceService.isAttendance(attendance)){
            //true????????????
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("?????????????????????????????????");
        }else{
            int count = attendanceService.addAtendance(attendance);
            if(count > 0){
                //????????????
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("????????????");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("??????????????????????????????");
            }
        }
        return ajaxResult;
    }

    @PostMapping("/deleteAttendance")
    @ResponseBody
    public AjaxResult deleteAttendance(Integer id){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = attendanceService.deleteAttendance(id);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("????????????");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("????????????");
            }
        } catch (Exception e) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("????????????,?????????");
            e.printStackTrace();
        }
        return ajaxResult;
    }
}
