import com.google.gson.Gson;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Map;

class Commit{
    Object sha;
    Object node_id;
    Object commit;
    Object url;
    Object html_url;
    Object comments_url;
    Object author;
    Object committer;
    Object parents;
    Object stats;
    Object files;
}
public class RESTCall {
    String makeRESTCall(String restUrl, String acceptHeaderValue)
            throws ClientProtocolException, IOException {
        Request request = Request.Get(restUrl);

        if (acceptHeaderValue != null && !acceptHeaderValue.isBlank()) {
            request.addHeader("Accept", acceptHeaderValue);
        }

        Content content = request.execute().returnContent();
        String jsonString = content.asString();
        System.out.println("content = " + jsonString);

        // To print response JSON, using GSON. Any other JSON parser can be used here.
        //Map jsonMap = gson.fromJson(jsonString, Map.class);
        return jsonString;
    }
    String makeRESTCall(String restUrl) throws ClientProtocolException, IOException {
        return makeRESTCall(restUrl, null);
    }
    void parseCommit(String jsonString){
        Gson gson = new Gson();
        Map map=gson.fromJson(jsonString,Map.class);
        //Commit commit = gson.fromJson(jsonString,Commit.class);
        //String tString=gson.fromJson(jsonString,)
    }
    public static void main(String[] args) {
        RESTCall r= new RESTCall();
        try {
            String str= r.makeRESTCall("https://api.github.com/repos/Distributed-System-Course/Student-Grade-Accessment-System/events");
            r.parseCommit(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
