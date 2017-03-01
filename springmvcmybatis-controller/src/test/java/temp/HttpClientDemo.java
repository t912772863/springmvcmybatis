package temp;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**演示常用的httpclient请求
 * Created by Administrator on 2017/3/1 0001.
 */
public class HttpClientDemo {
    public static void main(String[] args) throws Exception {
       testPost();
    }

    public static void testGet(){
        String urlNameString = "http://localhost:8080/common/refreshSystemCache";
        String result = "";
        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(urlNameString);//这里发送get请求
            // 获取当前客户端对象
            HttpClient httpClient = new DefaultHttpClient();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);

            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public static void testPost() throws Exception {
        String url = "http://localhost:8080/common/refreshSystemCache";
//POST的URL
        HttpPost httppost = new HttpPost(url);
//建立HttpPost对象
        List<NameValuePair> params = new ArrayList<NameValuePair>();
//建立一个NameValuePair数组，用于存储欲传送的参数
        params.add(new BasicNameValuePair("pwd", "2544"));
        params.add(new BasicNameValuePair("name", "tianxiong"));
//添加参数
        httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//设置编码
        HttpResponse response = new DefaultHttpClient().execute(httppost);
//发送Post,并返回一个HttpResponse对象
        if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }
    }
}
