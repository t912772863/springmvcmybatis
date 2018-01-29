package temp;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示常用的httpclient请求
 * Created by Administrator on 2017/3/1 0001.
 */
public class HttpClientDemo {
    public static void main(String[] args) throws Exception {
        testPostOfJson();

    }

    /**
     * get请求
     */
    public static void testGet() {
        String urlNameString = "http://183.56.132.30:28018/sms/smgsend/messageuser/sendMoibleMessageBnetWithCount?";
        String result = "";
        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(urlNameString+"CD2BC35D380050D7645AC0B504CF9CF918EF94799CCC6B2062DC17EE3D7AE50DA9508272249BB7C9F55300D68636EF4105AFFFEEA59C62F853FFED9A3589E5EC75FC2A44543CF8513978CB57F37952A9C9BCFD2B1ACA44352B9B3B87C6CE27523B432F4B3B52B802B47393B15448ED94435AFFF7A9C7EF150FAD6E464DB93C2C");//这里发送get请求
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

    /**
     * 表单形式的post请求
     * @throws Exception
     */
    public static void testPost() throws Exception {
        String url = "http://183.56.132.30:28018/sms/smgsend/messageuser/sendMoibleMessageBnetWithCount?";
//POST的URL
        HttpPost httppost = new HttpPost(url);
//建立HttpPost对象
        List<NameValuePair> params = new ArrayList<NameValuePair>();
//建立一个NameValuePair数组，用于存储欲传送的参数
        params.add(new BasicNameValuePair("receiveNumber", ""));
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

    /**
     * json串开式的post请求
     * @throws Exception
     */
    public static void testPostOfJson() throws Exception {
//        String url = "http://localhost:8083/test/testPush";
        String url = "http://121.15.167.229:18080/SendMailModule/SMSUplinkServlet";
//        String url = "http://localhost:8085/SendMailModule/SMSUplinkServlet";
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;

        String[] strArr = new String[]{"13510272496", "15000000001"};
        JSONObject obj = new JSONObject();
        obj.put("receiveNumber", strArr);
        obj.put("smsContent", "test content");
        obj.put("power", 0);
        obj.put("shorNumber", 12345678);
        System.out.println(obj.toJSONString());
        //解决中文乱码问题
        StringEntity entity = new StringEntity(obj.toJSONString(), "utf-8");
//        StringEntity entity = new StringEntity("<?xml version=\"1.0\" encoding=\"GBK\"?><delivers><deliver><corp_id>moSMS</corp_id><mobile>13510272498</mobile><ext>10658139991937</ext><time>2017-04-13 06:28:21</time><content></content></deliver></delivers>", "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        HttpResponse resp = client.execute(httpPost);
        System.out.println(resp.toString());

        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        }
        System.out.println(respContent);
    }
}
