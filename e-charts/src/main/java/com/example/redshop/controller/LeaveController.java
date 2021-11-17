package com.example.redshop.controller;

import com.example.redshop.domain.Leave;
import com.example.redshop.service.LeaveService;
import com.example.redshop.util.AjaxResult;
import com.example.redshop.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @RequestMapping("leave_list")
    public String leaveList(){
        return "/leave/leaveList";
    }

    @PostMapping("/getLeaveList")
    @ResponseBody
    public Object getClazzList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                               @RequestParam(value = "rows", defaultValue = "100")Integer rows,
                               @RequestParam(value = "buyerid", defaultValue = "0")String buyerid,
                               String from){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!buyerid.equals("0"))  paramMap.put("buyerId",buyerid);
        PageBean<Leave> pageBean = leaveService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }

    @PostMapping("/addLeave")
    @ResponseBody
    public AjaxResult addLeave(Leave leave){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = leaveService.addLeave(leave);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("添加成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("系统异常，请重试");
        }
        return ajaxResult;
    }

    @PostMapping("/editLeave")
    @ResponseBody
    public AjaxResult editLeave(Leave leave){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = leaveService.editLeave(leave);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("系统异常，请重试");
        }
        return ajaxResult;
    }


    @PostMapping("/checkLeave")
    @ResponseBody
    public AjaxResult checkLeave(Leave leave){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = leaveService.checkLeave(leave);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("审批成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("审批失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("系统异常，请重试");
        }
        return ajaxResult;
    }


    @PostMapping("/deleteLeave")
    @ResponseBody
    public AjaxResult deleteLeave(Integer id){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = leaveService.deleteLeave(id);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("删除成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("系统异常，请重试");
        }
        return ajaxResult;
    }


}
