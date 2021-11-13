package com;

import com.google.gson.Gson;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.ArrayList;

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
        if(token!=null)
        request.addHeader("access_token", token);
        else
            System.out.println(restUrl);

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
            row.put("data",root.getCreated_at());
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
        String token="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";
        ArrayList<LinkedTreeMap<String,Object>> res= r.returnTable(url,token);
        for (LinkedTreeMap<String,Object> row:res){
            for (String key : row.keySet()){
                System.out.print(key+"\t\t\t\t\t\t");
            }
            System.out.println();
            for (String key : row.keySet()){
                System.out.print(row.get(key)+"\t");
            }
            System.out.println();
        }
    }
}
