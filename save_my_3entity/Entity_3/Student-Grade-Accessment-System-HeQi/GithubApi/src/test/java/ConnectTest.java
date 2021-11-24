import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class ConnectTest {
    String makeRESTCall(String restUrl, String acceptHeaderValue,String token)
            throws ClientProtocolException, IOException {
        Request request = Request.Get(restUrl);
        request.socketTimeout(30000);
        if (acceptHeaderValue != null && !acceptHeaderValue.isBlank()) {
            request.addHeader("Accept", acceptHeaderValue);
        }
        if(token!=null){
            request.addHeader("Authorization", "token "+token);
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
    public String makeRESTCall(String restUrl) throws ClientProtocolException, IOException {
        return makeRESTCall(restUrl, null,null);
    }

    public static void main(String[] args) {
        ConnectTest c = new ConnectTest();
        //System.setProperty("http.proxySet", "true");
        System.setProperty("http.proxySet", "true");
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "7891");
        try {
            long startTime = System.currentTimeMillis(); //程序开始记录时间
            c.makeRESTCall(
                    "https://api.github.com/repos/Distributed-System-Course/Student-Grade-Accessment-System/events?per_page=100&page=1",
                    "",
                    "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"
            );
            long endTime   = System.currentTimeMillis(); //程序结束记录时间
            long TotalTime = endTime - startTime;       //总消耗时间
            System.out.println("耗时："+TotalTime/1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
