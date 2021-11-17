package com.example.redshop.controller;

import com.example.redshop.domain.seller;
import com.example.redshop.util.*;
import com.example.redshop.service.sellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/seller")
public class sellerController {

    @Autowired
    private sellerService sellerService;


    @RequestMapping("/seller_list")
    public String sellerList(){
        return "/seller/sellerList";
    }


    @PostMapping("/getsellerList")
    @ResponseBody
    public Object getsellerList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100")Integer rows,
                                 String sellerName,
                                 @RequestParam(value = "clazzid", defaultValue = "0")String clazzid, String from, HttpSession session){
        Map<String,Object> paramMap = new HashMap();
        System.out.println("in seller");
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!StringUtils.isEmpty(sellerName))  paramMap.put("username",sellerName);
        if(!clazzid.equals("0"))  paramMap.put("clazzid",clazzid);

        //判断是买家还是卖家权限
        seller seller = (seller) session.getAttribute(Const.seller);
        if(!StringUtils.isEmpty(seller)){
            //是卖家权限，只能查询自己的信息
            paramMap.put("sellerid",seller.getId());
        }

        PageBean<seller> pageBean = sellerService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }


    @PostMapping("/deleteseller")
    @ResponseBody
    public AjaxResult deleteseller(Data data){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            File fileDir = UploadUtil.getImgDirFile();
            for(Integer id : data.getIds()){
                seller byId = sellerService.findById(id);
                if(!byId.getPhoto().isEmpty()){
                    File file = new File(fileDir.getAbsolutePath() + File.separator + byId.getPhoto());
                    if(file != null){
                        file.delete();
                    }
                }
            }
            int count = sellerService.deleteseller(data.getIds());
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
            ajaxResult.setMessage("删除失败");
        }
        return ajaxResult;
    }


    @RequestMapping("/addseller")
    @ResponseBody
    public AjaxResult addseller(@RequestParam("file") MultipartFile[] files, seller seller) throws IOException {

        AjaxResult ajaxResult = new AjaxResult();
        seller.setSn(SnGenerateUtil.generatesellerSn(seller.getClazzId()));

        // 存放上传图片的文件夹
        File fileDir = UploadUtil.getImgDirFile();
        for(MultipartFile fileImg : files){

            // 拿到文件名
            String extName = fileImg.getOriginalFilename().substring(fileImg.getOriginalFilename().lastIndexOf("."));
            String uuidName = UUID.randomUUID().toString();

            try {
                // 构建真实的文件路径
                File newFile = new File(fileDir.getAbsolutePath() + File.separator +uuidName+ extName);

                // 上传图片到 -》 “绝对路径”
                fileImg.transferTo(newFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
            seller.setPhoto(uuidName+extName);
        }
        //保存买家信息到数据库
        try{
            int count = sellerService.addseller(seller);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("保存成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("保存失败");
        }

        ajaxResult.setSuccess(true);
        return ajaxResult;
    }

    @PostMapping("/editseller")
    @ResponseBody
    public AjaxResult editseller(@RequestParam("file") MultipartFile[] files,seller seller){
        AjaxResult ajaxResult = new AjaxResult();

        // 存放上传图片的文件夹
        File fileDir = UploadUtil.getImgDirFile();
        for(MultipartFile fileImg : files){

            String name = fileImg.getOriginalFilename();
            if(name.equals("")){
                break;
            }

            // 拿到文件名
            String extName = fileImg.getOriginalFilename().substring(fileImg.getOriginalFilename().lastIndexOf("."));
            String uuidName = UUID.randomUUID().toString();

            try {
                // 构建真实的文件路径
                File newFile = new File(fileDir.getAbsolutePath() + File.separator +uuidName+ extName);
                // 上传图片到 -》 “绝对路径”
                fileImg.transferTo(newFile);

                seller byId = sellerService.findById(seller.getId());
                File file = new File(fileDir.getAbsolutePath() + File.separator + byId.getPhoto());
                if(file != null){
                    file.delete();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            seller.setPhoto(uuidName+extName);
        }

        try{
            int count = sellerService.editseller(seller);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }
}
