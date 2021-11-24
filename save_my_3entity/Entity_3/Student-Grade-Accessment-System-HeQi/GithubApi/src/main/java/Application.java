import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Application {
    /**
     * This method will make a REST GET call for this URL using Apache http client &
     * fluent library.
     *
     * Then parse response using GSON & return parsed Map.
     */
    private static Map makeRESTCall(String restUrl, String acceptHeaderValue)
            throws ClientProtocolException, IOException {
        Request request = Request.Get(restUrl);
        request.addHeader("User-Agent","Awesome-Octocat-App");//请求头 必须加User-Agent
        if (acceptHeaderValue != null && !acceptHeaderValue.isBlank()) {
            request.addHeader("Accept", acceptHeaderValue);
        }

        Content content = request.execute().returnContent();
        String jsonString = content.asString();
        // System.out.println("content = " + jsonString);
        Gson gson=new Gson();
        // To print response JSON, using GSON. Any other JSON parser can be used here.
        Map jsonMap = gson.fromJson(jsonString, Map.class);
        return jsonMap;
    }

    private static Map makeRESTCall(String restUrl) throws ClientProtocolException, IOException {
        return makeRESTCall(restUrl, null);
    }

    public static void main(String[] args) {
        try {
            Map res=Application.makeRESTCall("https://api.github.com/repos/Distributed-System-Course/Student-Grade-Accessment-System/commits"
            );
            Set<String> keys=res.keySet();
            for (String key:keys){
                Object value=res.get(key);
                System.out.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
