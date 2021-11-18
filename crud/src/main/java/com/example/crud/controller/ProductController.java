package com.example.crud.controller;

import com.example.crud.entity.Product;
import com.example.crud.entity.User;
import com.example.crud.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    //产品信息的展示
    @RequestMapping("/plist")
    public String list(Model model ,@RequestParam(value = "start", defaultValue = "0") int start,
                       @RequestParam(value = "size", defaultValue = "5") int size){
        PageHelper.startPage(start,size,"id asc");
        List<Product> product= productService.pList();
//        PageInfo<Product> page = new PageInfo<>(product);
//        model.addAttribute("pages", page);
        return "productlist";

    }
    @RequestMapping("/findProduct")
    public String findProduct(int pid, Model model) throws Exception {
        Product product= productService.findProductById(pid);
        model.addAttribute("product", product);

        return "progress";
    }
    @RequestMapping("/productlist")
    @ResponseBody
    public List<Product> list2( ){

        List<Product> product= productService.pList();


        return product;

    }
    @RequestMapping("/ptest")

    public String list3(Model model ){

        List<Product> product= productService.pList();

        model.addAttribute("page2", product);
        return "head";

    }
}
