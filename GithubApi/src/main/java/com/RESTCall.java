package com;

import com.google.gson.Gson;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;


import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class RESTCall {
    ArrayList<LinkedTreeMap<String,Object>> table;
    public RESTCall(){
        table= new ArrayList<LinkedTreeMap<String,Object>>();
    }
    String makeRESTCall(String restUrl, String acceptHeaderValue,String token)
            throws ClientProtocolException, IOException {
        Request request = Request.Get(restUrl);
        if (acceptHeaderValue != null && !acceptHeaderValue.isBlank()) {
            request.addHeader("Accept", acceptHeaderValue);
        }
        if(token!=null){
            request.addHeader("access_token", token);
        }
        else
            System.out.println(restUrl);
        request.addHeader("User-Agent", "Mozilla/5.0");
        Content content = request.execute().returnContent();
        String jsonString = content.asString();
        //System.out.println("content = " + jsonString);

        // To print response JSON, using GSON. Any other JSON parser can be used here.
        //Map jsonMap = gson.fromJson(jsonString, Map.class);
        return jsonString;
    }
    String makeRESTCall(String restUrl) throws ClientProtocolException, IOException {
        return makeRESTCall(restUrl, null,null);
    }
    String parseDate(String raw){
        String str=raw;
        str=str.replaceAll("T"," ");
        str=str.replaceAll("Z","");
        SimpleDateFormat beijingSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        beijingSDF.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));//北京时区
        SimpleDateFormat gitSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //git 时间格式
        gitSDF.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));  // 设置git时区
        Date date = null;  // 将字符串时间按git时间解析成Date对象
        try {
            date = gitSDF.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return beijingSDF.format(date).toString();
    }
    void parseEvent(String jsonString,String token){
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(jsonString).getAsJsonArray();
        Gson gson = new Gson();
        //对于数组形的json
        for(JsonElement jsonElement:jsonArray){
            POJO.Event.Root root = gson.fromJson(jsonElement , POJO.Event.Root.class);
            if (root==null) break;
            LinkedTreeMap<String,Object> row= new LinkedTreeMap<String,Object>();
            row.put("author",root.getActor().getLogin());
            row.put("date",parseDate(root.getCreated_at()));
            row.put("project",root.getRepo().getName());
            if(root.getPayload().getCommits()!=null&& !root.getPayload().getCommits().isEmpty()){
                String commitUrl=root.getPayload().getCommits().get(0).getUrl();
                parseCommit(commitUrl,token,row);
            }
            table.add(row);
        }
    }
    void parseCommit(String url,String token,LinkedTreeMap<String,Object>row){
        String jsonString = null;
        try {
            jsonString = makeRESTCall(url,null,token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        POJO.Commits.Root root=gson.fromJson(jsonString,POJO.Commits.Root.class);
        row.put("addLines",root.getStats().getAdditions());
        row.put("deleteLines",root.getStats().getDeletions());
        row.put("totalLines",root.getStats().getTotal());
    }
    public ArrayList<LinkedTreeMap<String,Object>> returnTable(String restfurl,String token){
        String str = null;
        try {
            ArrayList<LinkedTreeMap<String,Object>> res = new ArrayList<LinkedTreeMap<String,Object>>();
            for (int i=1;i<=3;++i){
                RESTCall restCall= new RESTCall();
                String url=new String(restfurl);
                url+="?per_page=100&page="+i;
                str=restCall.makeRESTCall(url,null,token);
                restCall.parseEvent(str,token);
                res.addAll(restCall.table);
            }

            //str = this.makeRESTCall(restfurl,null,token);
            //this.parseEvent(str);
            //ArrayList<LinkedTreeMap<String,Object>> res = new ArrayList<LinkedTreeMap<String,Object>>();
            //res.addAll(this.table);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        RESTCall r= new RESTCall();
        //String str= r.makeRESTCall("https://api.github.com/repos/Distributed-System-Course/Student-Grade-Accessment-System/events");
        //r.parseEvent(str);
        String url="https://api.github.com/repos/Distributed-System-Course/Student-Grade-Accessment-System/events";
        //String token="28765232539e7ec571d1a97543c2063aa3c3b54e";//APP
        String token="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";//personal
        ArrayList<LinkedTreeMap<String,Object>> res= r.returnTable(url,token);
        for (LinkedTreeMap<String,Object> row:res){
            for (String key : row.keySet()){
                System.out.print(key+"\t\t\t\t\t\t");
            }
            System.out.println();
            for (String key : row.keySet()){
                System.out.print(row.get(key)+"\t\t\t\t\t\t");
            }
            System.out.println();
        }
    }
}
