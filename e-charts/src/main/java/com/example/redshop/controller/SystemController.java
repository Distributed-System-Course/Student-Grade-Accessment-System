package com.example.redshop.controller;

import com.example.redshop.domain.Admin;
import com.example.redshop.domain.buyer;
import com.example.redshop.domain.seller;
import com.example.redshop.service.buyerService;
import com.example.redshop.util.AjaxResult;
import com.example.redshop.util.CpachaUtil;
import com.example.redshop.service.AdminService;
import com.example.redshop.service.sellerService;
import com.example.redshop.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private buyerService buyerService;
    @Autowired
    private sellerService sellerService;

    @GetMapping("/login")
    public String login(){
        return "/login";
    }


    @PostMapping("/login")
    @ResponseBody
    public AjaxResult submitlogin(String username, String password, String code, String type,
                                  HttpSession session){
        AjaxResult ajaxResult = new AjaxResult();
        if(StringUtils.isEmpty(username)){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写用户名");
            return ajaxResult;
        }
        if(StringUtils.isEmpty(password)){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写密码");
            return ajaxResult;
        }
        if(StringUtils.isEmpty(code)){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填验证码");
            return ajaxResult;
        }
        if(StringUtils.isEmpty(session.getAttribute(Const.CODE))){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("会话时间过长，请刷新");
            return ajaxResult;
        }else{
            if(!code.equalsIgnoreCase((String) session.getAttribute(Const.CODE))){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("验证码错误");
                return ajaxResult;
            }
        }
        //数据库校验
        switch (type){
            case "1":{ //管理员
                Admin admin = new Admin();
                admin.setPassword(password);
                admin.setUsername(username);
                Admin ad = adminService.findByAdmin(admin);
                if(StringUtils.isEmpty(ad)){
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.ADMIN,ad);
                session.setAttribute(Const.USERTYPE,"1");
                break;
            }
            case "2":{
                buyer buyer = new buyer();
                buyer.setPassword(password);
                buyer.setUsername(username);
                buyer st = buyerService.findBybuyer(buyer);
                if(StringUtils.isEmpty(st)){
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.buyer,st);
                session.setAttribute(Const.USERTYPE,"2");
                break;
            }
            case "3":{
                seller seller = new seller();
                seller.setPassword(password);
                seller.setUsername(username);
                seller tr = sellerService.findByseller(seller);
                if(StringUtils.isEmpty(tr)){
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.seller,tr);
                session.setAttribute(Const.USERTYPE,"3");
                break;
            }
        }
        ajaxResult.setMessage("登陆成功");
        return ajaxResult;
    }


    @GetMapping("/checkCode")
    public void generateCpacha(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value="vl",defaultValue="4",required=false) Integer vl,
                               @RequestParam(value="w",defaultValue="110",required=false) Integer w,
                               @RequestParam(value="h",defaultValue="39",required=false) Integer h){
        CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(Const.CODE, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/index")
    public String index(){
        return "/system/index";
    }



    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/login";
    }


    @RequestMapping("/getPhoto")
    @ResponseBody
    public AjaxResult getPhoto(@RequestParam(value = "sid",defaultValue = "0") Integer sid,
                               @RequestParam(value = "tid",defaultValue = "0")Integer tid){
        AjaxResult ajaxResult = new AjaxResult();
        if(sid != 0){
            buyer buyer = buyerService.findById(sid);
            ajaxResult.setImgurl(buyer.getPhoto());
            return ajaxResult;
        }
        if(tid!=0){
            seller seller = sellerService.findById(tid);
            ajaxResult.setImgurl(seller.getPhoto());
            return ajaxResult;
        }

        return ajaxResult;
    }


    @GetMapping("/personalView")
    public String personalView(){
        return "/system/personalView";
    }



    @PostMapping("/editPassword")
    @ResponseBody
    public AjaxResult editPassword(String password,String newpassword,HttpSession session){
        AjaxResult ajaxResult = new AjaxResult();
        String usertype = (String) session.getAttribute(Const.USERTYPE);
        if (usertype.equals("1")){
            //管理员
            Admin admin = (Admin)session.getAttribute(Const.ADMIN);
            if(!password.equals(admin.getPassword())){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            admin.setPassword(newpassword);
            try{
                int count = adminService.editPswdByAdmin(admin);
                if(count > 0){
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                }else{
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            }catch (Exception e){
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        if(usertype.equals("2")){
            //学生
            buyer buyer = (buyer)session.getAttribute(Const.buyer);
            if(!password.equals(buyer.getPassword())){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            buyer.setPassword(newpassword);
            try{
                int count = buyerService.editPswdBybuyer(buyer);
                if(count > 0){
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                }else{
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            }catch (Exception e){
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        if(usertype.equals("3")){
            //教师
            seller seller = (seller) session.getAttribute(Const.seller);
            if(!password.equals(seller.getPassword())){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            seller.setPassword(newpassword);
            try{
                int count = sellerService.editPswdByseller(seller);
                if(count > 0){
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                }else{
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            }catch (Exception e){
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        return ajaxResult;
    }

}
