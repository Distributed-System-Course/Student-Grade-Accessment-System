package com.example.redshop.controller;

import com.example.redshop.domain.*;
import com.example.redshop.service.*;
import com.example.redshop.util.AjaxResult;
import com.example.redshop.util.PageBean;
import com.example.redshop.util.Const;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.redshop.service.sellerService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trade")
public class tradeController {

    @Autowired
    private tradeService tradeService;
    @Autowired
    private buyerService buyerService;
    @Autowired
    private productService productService;
    @Autowired
    private sellerService sellerService;
    @Autowired
    private SelectedproductService selectedproductService;


    @GetMapping("/trade_list")
    public String tradeList(){
        return "/trade/tradeList";
    }



    @RequestMapping("/gettradeList")
    @ResponseBody
    public Object gettradeList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                    @RequestParam(value = "rows", defaultValue = "100")Integer rows,
                                    @RequestParam(value = "buyerid", defaultValue = "0")String buyerid,
                                    @RequestParam(value = "productid", defaultValue = "0")String productid,
                               @RequestParam(value = "sellerid", defaultValue = "0")String sellerid,
                                    String from, HttpSession session){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        System.out.println(buyerid);
        System.out.println(productid);
        if(!buyerid.equals("0"))  paramMap.put("buyerid",buyerid);

        if(!productid.equals("0"))  paramMap.put("productid",productid);
        if(!sellerid.equals("0"))  paramMap.put("sellerid",sellerid);

        //判断是老师还是学生权限
        buyer buyer = (buyer) session.getAttribute(Const.buyer);
        if(!StringUtils.isEmpty(buyer)){
            //是学生权限，只能查询自己的信息
            paramMap.put("buyerid",buyer.getId());
            //System.out.println(buyer.getId());
        }

        seller seller = (seller) session.getAttribute(Const.seller);
        if(!StringUtils.isEmpty(seller)){
            //是学生权限，只能查询自己的信息
            paramMap.put("sellerid",seller.getId());
            //System.out.println(buyer.getId());
        }

        PageBean<trade> pageBean = tradeService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }



    @PostMapping("/addtrade")
    @ResponseBody
    public AjaxResult addtrade(trade trade){
        AjaxResult ajaxResult = new AjaxResult();
        //判断是否已录入交易
        if(tradeService.istrade(trade)){
            //true为已签到
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("已录入，请勿重复录入！");
        }else{
            int count = tradeService.addtrade(trade);
            if(count > 0){
                //签到成功
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("录入成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("系统错误，请重新录入");
            }
        }
        return ajaxResult;
    }



    @PostMapping("/edittrade")
    @ResponseBody
    public AjaxResult edittrade(trade trade){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = tradeService.edittrade(trade);
            if(count > 0){
                //签到成功
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("系统错误，请重新修改");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("系统错误，请重新修改");
        }
        return ajaxResult;
    }


    @PostMapping("/deletetrade")
    @ResponseBody
    public AjaxResult deletetrade(Integer id){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = tradeService.deletetrade(id);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("删除成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("系统错误，请重新删除");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("系统错误，请重新删除");
        }
        return ajaxResult;
    }


    @PostMapping("/importtrade")
    @ResponseBody
    public void importtrade(@RequestParam("importtrade") MultipartFile importtrade, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        try {
            System.out.println("enter import!");
            InputStream inputStream = importtrade.getInputStream();
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);
            int count = 0;
            String errorMsg = "";
            for(int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++){
                XSSFRow row = sheetAt.getRow(rowNum); // 获取第rowNum行
                //第0列
                XSSFCell cell = row.getCell(0); // 获取第rowNum行的第0列 即坐标（rowNum，0）
                if(cell == null){
                    errorMsg += "第" + rowNum + "行买家缺失！\n";
                    continue;
                }
                //第1列
                cell = row.getCell(1);
                if(cell == null){
                    errorMsg += "第" + rowNum + "行卖家缺失！\n";
                    continue;
                }

                //第2列
                cell = row.getCell(2);
                if(cell == null){
                    errorMsg += "第" + rowNum + "行商品缺失！\n";
                    continue;
                }
                //第3列
                cell = row.getCell(3);
                if(cell == null){
                    errorMsg += "第" + rowNum + "行单价缺失！\n";
                    continue;
                }
                double tradeValue = cell.getNumericCellValue();
                //第4列
                cell = row.getCell(4);
                if(cell == null){
                    errorMsg += "第" + rowNum + "行购买数量缺失！\n";
                    continue;
                }
                int productNumValue = (int) cell.getNumericCellValue();
                //第5列
                cell = row.getCell(5);
                if(cell == null){
                    errorMsg += "第" + rowNum + "行总共花销缺失！\n";
                    continue;
                }
                double totalTradeValue = cell.getNumericCellValue();
                //第6列
                cell = row.getCell(6);
                String remark = null;
                if(cell != null){
                    remark = cell.getStringCellValue();
                }
                System.out.println("finish cell!");
                //将学生，课程转换为id,存入数据库
                // 1)首先获取对应的id
                int buyerId = buyerService.findByName(row.getCell(0).getStringCellValue());
                int productId = productService.findByName(row.getCell(2).getStringCellValue());
                int sellerId = sellerService.findByName(row.getCell(1).getStringCellValue());
                // 2)判断是否已存在数据库中
                trade trade = new trade();
                trade.setbuyerId(buyerId);
                trade.setsellerId(sellerId);
                trade.setproductId(productId);
                trade.settrade(tradeValue);
                trade.setproduct_num(productNumValue);
                trade.settotal_trade(totalTradeValue);
                trade.setRemark(remark);
                if(!tradeService.istrade(trade)){
                    // 3)存入数据库
                    int i = tradeService.addtrade(trade);
                    if(i > 0){
                        count ++ ;
                    }
                }else{
                    errorMsg += "第" + rowNum + "行已录入，不重复录入！\n";
                }
            }
            errorMsg += "成功录入" + count + "条交易信息！";
            response.getWriter().write("<div id='message'>"+errorMsg+"</div>");

        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.getWriter().write("<div id='message'>上传错误</div>");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }



    @RequestMapping("/exporttrade")
    @ResponseBody
    private void exporttrade(HttpServletResponse response,trade trade,HttpSession session) {
        //获取当前登录用户类型
        buyer buyer = (buyer) session.getAttribute(Const.buyer);
        if(!StringUtils.isEmpty(buyer)){
            //如果是买家，只能查看自己的信息
            trade.setbuyerId(buyer.getId());
        }
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("trade_list_sid_"+trade.getbuyerId()+"_cid_"+trade.getbuyerId()+".xls", "UTF-8"));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/octet-stream");
            ServletOutputStream outputStream = response.getOutputStream();
            List<trade> tradeList = tradeService.getAll(trade);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            XSSFSheet createSheet = xssfWorkbook.createSheet("交易列表");
            XSSFRow createRow = createSheet.createRow(0);
            createRow.createCell(0).setCellValue("买家");
            createRow.createCell(1).setCellValue("卖家");
            createRow.createCell(2).setCellValue("商品");
            createRow.createCell(3).setCellValue("单价");
            createRow.createCell(4).setCellValue("购买数量");
            createRow.createCell(5).setCellValue("总共花销");
            createRow.createCell(6).setCellValue("备注");
            //实现将数据装入到excel文件中
            int row = 1;
            for( trade s:tradeList){
                createRow = createSheet.createRow(row++);
                createRow.createCell(0).setCellValue(s.getbuyerName());
                createRow.createCell(1).setCellValue(s.getsellerName());
                createRow.createCell(2).setCellValue(s.getproductName());
                createRow.createCell(3).setCellValue(s.gettrade());
                createRow.createCell(4).setCellValue(s.getproduct_num());
                createRow.createCell(5).setCellValue(s.gettotal_trade());
                createRow.createCell(6).setCellValue(s.getRemark());
            }
            xssfWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/tradeStats")
    public String tradeStats(){
        return "/trade/tradeStats";
    }

    @RequestMapping("/tradeStats2")
    public String tradeStats2(){
        return "/trade/tradeStats2";
    }

    @RequestMapping("/tradeStats3")
    public String tradeStats3(){
        return "/trade/tradeStats3";
    }



    @RequestMapping("/gettradeStatsList")
    @ResponseBody
    public Object gettradeStatsList(@RequestParam(value = "productid", defaultValue = "0")Integer productid,
                                        String searchType){
        AjaxResult ajaxResult = new AjaxResult();
        if(searchType.equals("avg")){
            tradeStats tradeStats = tradeService.getAvgStats(productid);

            List<Double> tradeList = new ArrayList<Double>();
            tradeList.add(tradeStats.getMax_trade());
            tradeList.add(tradeStats.getMin_trade());
            tradeList.add(tradeStats.getAvg_trade());

            List<String> avgStringList = new ArrayList<String>();
            avgStringList.add("最高交易");
            avgStringList.add("最低交易");
            avgStringList.add("平均交易");

            Map<String, Object> retMap = new HashMap<String, Object>();
            retMap.put("productName", tradeStats.getproductName());
            retMap.put("tradeList", tradeList);
            retMap.put("avgList", avgStringList);
            retMap.put("type", "success");

            return retMap;
        }

        trade trade = new trade();
        trade.setproductId(productid);
        List<trade> tradeList = tradeService.getAll(trade);


        List<Integer> numberList = new ArrayList<Integer>();
        numberList.add(0);
        numberList.add(0);
        numberList.add(0);
        numberList.add(0);
        numberList.add(0);

        List<String> rangeStringList = new ArrayList<String>();
        rangeStringList.add("50元以下");
        rangeStringList.add("50~70元");
        rangeStringList.add("70~80元");
        rangeStringList.add("80~90元");
        rangeStringList.add("90~100元");

        String productName = "";

        for(trade sc : tradeList){
            productName = sc.getproductName();  //获取商品
            double tradeValue = sc.gettrade();//获取交易
            if(tradeValue < 60){
                numberList.set(0, numberList.get(0)+1);
                continue;
            }
            if(tradeValue <= 70 && tradeValue >= 50){
                numberList.set(1, numberList.get(1)+1);
                continue;
            }
            if(tradeValue <= 80 && tradeValue > 70){
                numberList.set(2, numberList.get(2)+1);
                continue;
            }
            if(tradeValue <= 90 && tradeValue > 80){
                numberList.set(3, numberList.get(3)+1);
                continue;
            }
            if(tradeValue <= 100 && tradeValue > 90){
                numberList.set(4, numberList.get(4)+1);
                continue;
            }
        }
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("productName", productName);
        retMap.put("numberList", numberList);
        retMap.put("rangeList", rangeStringList);
        retMap.put("type", "success");
        return retMap;
    }

    @RequestMapping("/gettradeStatsList2")
    @ResponseBody
    public Object gettradeStatsList2(@RequestParam(value = "sellername", defaultValue = " ")String sellername,
                                    String searchType){
        Integer sellerid = sellerService.findByName(sellername);
        AjaxResult ajaxResult = new AjaxResult();
        if(searchType.equals("avg")){

            tradeStats2 tradeStats2 = tradeService.getAvgStats2(sellerid);

            List<Double> tradeList2 = new ArrayList<Double>();
            tradeList2.add(tradeStats2.getMax_trade());
            tradeList2.add(tradeStats2.getMin_trade());
            tradeList2.add(tradeStats2.getAvg_trade());

            List<String> avgStringList2 = new ArrayList<String>();
            avgStringList2.add("最高交易");
            avgStringList2.add("最低交易");
            avgStringList2.add("平均交易");

            Map<String, Object> retMap2 = new HashMap<String, Object>();
            retMap2.put("sellerName", tradeStats2.getsellerName());
            retMap2.put("tradeList", tradeList2);
            retMap2.put("avgList", avgStringList2);
            retMap2.put("type", "success");

            return retMap2;
        }

        trade trade = new trade();
        trade.setsellerId(sellerid);
        List<trade> tradeList = tradeService.getAll(trade);


        List<Integer> numberList = new ArrayList<Integer>();
        numberList.add(0);
        numberList.add(0);
        numberList.add(0);
        numberList.add(0);
        numberList.add(0);

        List<String> rangeStringList = new ArrayList<String>();
        rangeStringList.add("50元以下");
        rangeStringList.add("50~70元");
        rangeStringList.add("70~80元");
        rangeStringList.add("80~90元");
        rangeStringList.add("90~100元");

        String sellerName = "";

        for(trade sc : tradeList){
            sellerName = sc.getsellerName();  //获取商品
            double tradeValue = sc.gettrade();//获取交易
            if(tradeValue < 60){
                numberList.set(0, numberList.get(0)+1);
                continue;
            }
            if(tradeValue <= 70 && tradeValue >= 50){
                numberList.set(1, numberList.get(1)+1);
                continue;
            }
            if(tradeValue <= 80 && tradeValue > 70){
                numberList.set(2, numberList.get(2)+1);
                continue;
            }
            if(tradeValue <= 90 && tradeValue > 80){
                numberList.set(3, numberList.get(3)+1);
                continue;
            }
            if(tradeValue <= 100 && tradeValue > 90){
                numberList.set(4, numberList.get(4)+1);
                continue;
            }
        }
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("sellerName", sellerName);
        retMap.put("numberList", numberList);
        retMap.put("rangeList", rangeStringList);
        retMap.put("type", "success");
        return retMap;
    }

    @RequestMapping("/gettradeStatsList3")
    @ResponseBody
    public Object gettradeStatsList3(@RequestParam(value = "buyername", defaultValue = " ")String buyername,
                                     String searchType){
        Integer buyerid = buyerService.findByName(buyername);
        AjaxResult ajaxResult = new AjaxResult();
        if(searchType.equals("avg")){

            tradeStats3 tradeStats3 = tradeService.getAvgStats3(buyerid);

            List<Double> tradeList3 = new ArrayList<Double>();
            tradeList3.add(tradeStats3.getMax_trade());
            tradeList3.add(tradeStats3.getMin_trade());
            tradeList3.add(tradeStats3.getAvg_trade());

            List<String> avgStringList3 = new ArrayList<String>();
            avgStringList3.add("最高交易");
            avgStringList3.add("最低交易");
            avgStringList3.add("平均交易");

            Map<String, Object> retMap3 = new HashMap<String, Object>();
            retMap3.put("sellerName", tradeStats3.getsellerName());
            retMap3.put("tradeList", tradeList3);
            retMap3.put("avgList", avgStringList3);
            retMap3.put("type", "success");

            return retMap3;
        }

        trade trade = new trade();
        trade.setbuyerId(buyerid);
        List<trade> tradeList = tradeService.getAll(trade);


        List<Integer> numberList = new ArrayList<Integer>();
        numberList.add(0);
        numberList.add(0);
        numberList.add(0);
        numberList.add(0);
        numberList.add(0);

        List<String> rangeStringList = new ArrayList<String>();
        rangeStringList.add("50元以下");
        rangeStringList.add("50~70元");
        rangeStringList.add("70~80元");
        rangeStringList.add("80~90元");
        rangeStringList.add("90~100元");

        String buyerName = "";

        for(trade sc : tradeList){
            buyerName = sc.getsellerName();  //获取商品
            double tradeValue = sc.gettrade();//获取交易
            if(tradeValue < 60){
                numberList.set(0, numberList.get(0)+1);
                continue;
            }
            if(tradeValue <= 70 && tradeValue >= 50){
                numberList.set(1, numberList.get(1)+1);
                continue;
            }
            if(tradeValue <= 80 && tradeValue > 70){
                numberList.set(2, numberList.get(2)+1);
                continue;
            }
            if(tradeValue <= 90 && tradeValue > 80){
                numberList.set(3, numberList.get(3)+1);
                continue;
            }
            if(tradeValue <= 100 && tradeValue > 90){
                numberList.set(4, numberList.get(4)+1);
                continue;
            }
        }
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("buyerName", buyerName);
        retMap.put("numberList", numberList);
        retMap.put("rangeList", rangeStringList);
        retMap.put("type", "success");
        return retMap;
    }

}
