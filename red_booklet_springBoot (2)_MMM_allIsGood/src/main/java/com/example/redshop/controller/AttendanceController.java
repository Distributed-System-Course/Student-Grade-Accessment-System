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

        //判断是老师还是学生权限
        buyer buyer = (buyer) session.getAttribute(Const.buyer);
        if(!StringUtils.isEmpty(buyer)){
            //是学生权限，只能查询自己的信息
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
        //通过学生id 查询 选课信息
        List<Selectedproduct> selectedproductList = selectedproductService.getAllBySid(Integer.parseInt(buyerid));
        //通过 选课信息中的课程id 查询 学生所选择的课程
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
        //判断是否已签到
        if(attendanceService.isAttendance(attendance)){
            //true为已签到
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("已签到，请勿重复签到！");
        }else{
            int count = attendanceService.addAtendance(attendance);
            if(count > 0){
                //签到成功
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("签到成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("系统错误，请重新签到");
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
                ajaxResult.setMessage("删除成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("删除失败");
            }
        } catch (Exception e) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("系统异常,请重试");
            e.printStackTrace();
        }
        return ajaxResult;
    }
}
