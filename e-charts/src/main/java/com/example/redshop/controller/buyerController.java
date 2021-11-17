package com.example.redshop.controller;

import com.example.redshop.domain.buyer;
import com.example.redshop.service.ClazzService;
import com.example.redshop.service.SelectedproductService;
import com.example.redshop.service.buyerService;
import com.example.redshop.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/buyer")
public class buyerController {

    @Autowired
    private buyerService buyerService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private SelectedproductService selectedproductService;

    @GetMapping("/buyer_list")
    public String buyerList(){
        return "/buyer/buyerList";
    }

    @RequestMapping("/getbuyerList")
    @ResponseBody
    public Object getbuyerList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100")Integer rows,
                                 String buyerName,
                                 @RequestParam(value = "clazzid", defaultValue = "0")String clazzid, String from, HttpSession session){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!StringUtils.isEmpty(buyerName))  paramMap.put("username",buyerName);
        if(!clazzid.equals("0"))  paramMap.put("clazzid",clazzid);

        //判断是买家还是卖家权限
        buyer buyer = (buyer) session.getAttribute(Const.buyer);
        if(!StringUtils.isEmpty(buyer)){
            //是买家权限，只能查询自己的信息
            paramMap.put("buyerid",buyer.getId());
        }

        PageBean<buyer> pageBean = buyerService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }

    /**
     * 删除买家
     * @param data
     * @return
     */
    @PostMapping("/deletebuyer")
    @ResponseBody
    public AjaxResult deletebuyer(Data data){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            List<Integer> ids = data.getIds();
            Iterator<Integer> iterator = ids.iterator();
            while (iterator.hasNext()){  //判断是否存在商品关联买家
                if(!selectedproductService.isbuyerId(iterator.next())){
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("无法删除,存在课程关联买家");
                    return ajaxResult;
                }
            }
            File fileDir = UploadUtil.getImgDirFile();
            for(Integer id : ids){
                buyer byId = buyerService.findById(id);
                if(!byId.getPhoto().isEmpty()){
                    File file = new File(fileDir.getAbsolutePath() + File.separator + byId.getPhoto());
                    if(file != null){
                        file.delete();
                    }
                }
            }
            int count = buyerService.deletebuyer(ids);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("全部删除成功");

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


    /**
     * 添加买家
     * @param files
     * @param buyer
     * @return
     * @throws IOException
     */
    @RequestMapping("/addbuyer")
    @ResponseBody
    public AjaxResult addbuyer(@RequestParam("file") MultipartFile[] files,buyer buyer) throws IOException {

        AjaxResult ajaxResult = new AjaxResult();
        buyer.setSn(SnGenerateUtil.generateSn(buyer.getClazzId()));

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
            buyer.setPhoto(uuidName+extName);
        }
        //保存买家信息到数据库
        try{
            int count = buyerService.addbuyer(buyer);
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

    /**
     * 修改买家信息
     * @param files
     * @param buyer
     * @return
     */
    @PostMapping("/editbuyer")
    @ResponseBody
    public AjaxResult editbuyer(@RequestParam("file") MultipartFile[] files,buyer buyer){
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

                buyer byId = buyerService.findById(buyer.getId());
                File file = new File(fileDir.getAbsolutePath() + File.separator + byId.getPhoto());
                if(file != null){
                    file.delete();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            buyer.setPhoto(uuidName+extName);
        }

        try{
            int count = buyerService.editbuyer(buyer);
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
