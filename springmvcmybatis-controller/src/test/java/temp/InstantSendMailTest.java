package temp;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 不间断连续发送测试
 *
 * @author hhb@richinfo.cn
 * @ClassName: InstantSendMailTest
 * @Description: TODO
 * @date 2015-12-11 下午02:28:50
 */
public class InstantSendMailTest {
    public static void sendMail2(String _receiverMail) throws UnsupportedEncodingException {
        String appKey = "553137c5-d0ee-4e24-bc38-3db42df3e833";//开发环境
        String appSecret = "9ba9666b-3dd1-45d5-8ef5-9df5678b672f";//;  IndustrySmsService

//        String appKey = "569f172e-f388-47e3-bf32-c299fdf30585";//自有环境
//        String appSecret= "d78521e1-9e18-4f2b-8669-a27468ae2e26";

//        String url = "http://121.15.167.229:18080/SendMailModule/IndustrySmsService";  // 开发环境
//        String url = "http://211.136.10.228:8580/SendMailModule/SendSmsMailService";   // 自有环境
//        String url = "http://172.16.16.84:8581/SendMailModule/IndustrySmsService";   // 自有环境
        String url = "http://localhost:8088/SendMailModule/IndustrySmsService";
//        String url = "http://openapi.mail.10086.cn:8580/SendMailModule/SendSmsMailService";
        String methodName = "sendmail";
        String uid = "13760869714";      //13842035301        //caixun_test              //发邮件人
        String receiverMail = _receiverMail;//"1351274169@hmg1.rd139.com";	//		//收件人
        String content = Base64.encode("邮件内容".getBytes("utf-8"));
        String smsContent = "【百度金融】恭喜，您获得百度金融商城特邀资格";
        String title = Base64.encode((smsContent).getBytes("UTF-8"));
        String email_title = Base64.encode("这是一个email_title".getBytes("UTF-8"));
        Date date = new Date();
        date.setTime(date.getTime() + 40000000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(date);
        String spnumber = "106581399910021586524";// 开发环境
//        String spnumber = "1065813988123";//自有环境

        String templateId = "1000295";
        String serviceType = "SMS";

        String beforeEncript = "app_key" + appKey + "app_secret" + appSecret + "content" + content
                + "email_title"+ email_title
                + "receiver_mail" + receiverMail
//                + "receiver_name" + receiver_name
                + "request_method" + methodName + "return_formatxml"
                + "sendsmspriority1"
                +"serviceType"+serviceType
                + "spnumber" + spnumber
//                + "templateId" + templateId
                + "timestamp" + timestamp
                //+"sendsmspriority2templateId"+templateId+"timestamp" + timestamp
                + "title" + title
//                + "username" + username
                + "usernumber" + uid + "version2.0";

        String afterEncript = encriptMD5(beforeEncript).toUpperCase();
        System.out.println("待加密字符串：" + beforeEncript);
        System.out.println("加密后的字符串" + afterEncript);
        StringBuffer testXML = new StringBuffer();
        testXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<sendmail>")
                .append("<sendsmspriority>1</sendsmspriority>")
//                .append("<templateId>"+templateId+"</templateId>")
                .append("<version>2.0</version>")
                .append("<request_method>sendmail</request_method>")
                .append("<app_key>" + appKey + "</app_key>")
                .append("<receiver_mail>" + receiverMail + "</receiver_mail>")
//                .append("<receiver_name>" + receiver_name + "</receiver_name>")
                .append("<content>" + content + "</content>")
                .append("<title>" + title + "</title>")
                .append("<spnumber>" + spnumber + "</spnumber>")
//                .append("<username>" + username + "</username>")
                .append("<return_format>xml</return_format>")
                .append("<app_secret>" + appSecret + "</app_secret>")
                .append("<usernumber>" + uid + "</usernumber>")
                .append("<sign_code>" + afterEncript + "</sign_code>")
                .append("<serviceType>"+serviceType+"</serviceType>")
                .append("<timestamp>" + timestamp + "</timestamp>")
                .append("<email_title>"+ email_title+"</email_title>")
                .append("</sendmail>");
        System.out.println("请求XML：" + testXML.toString());
        long startTime = System.currentTimeMillis();
        String resStr = httpRequest(testXML.toString(), url);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - startTime) / 1000 + "返回XML：" + resStr);
    }

    public static String encriptMD5(String text) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static String httpRequest(String reqXml, String reqUrl) {
        URL url = null;
        HttpURLConnection httpurlconnection = null;
        BufferedReader respin = null;
        StringBuffer respData = null;
        String respStr = null;
        try {
            url = new URL(reqUrl);
            httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setConnectTimeout(10000);// 连接超时时间
            httpurlconnection.setReadTimeout(30000);// 读超时时间
            httpurlconnection.setDoOutput(true);
            httpurlconnection.setDoInput(true);
            httpurlconnection.setRequestMethod("POST");
            httpurlconnection.setRequestProperty("Content-Type",
                    "text/xml;charset=UTF-8");
            httpurlconnection.getOutputStream().write(reqXml.getBytes("UTF-8"));
            httpurlconnection.getOutputStream().flush();
            httpurlconnection.getOutputStream().close();
            int code = httpurlconnection.getResponseCode();
            respin = new BufferedReader(new InputStreamReader(
                    httpurlconnection.getInputStream(), "utf-8"));
            respData = new StringBuffer(300);
            while ((respStr = respin.readLine()) != null) {
                respData.append(respStr);
            }
            return respData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            respData = null;
            if (respin != null) {
                try {
                    respin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                respin = null;
            }
            if (httpurlconnection != null) {
                httpurlconnection.disconnect();
                httpurlconnection = null;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        // 黑: 13777718896  13800138000  13510272496  18928789779  13360032000
        sendMail2("13510272496@139.com");
//        sendMail2("13800138000@139.com");
//        sendMail2("13777718896@139.com");
    }

    @SuppressWarnings("unused")
    private static String[] distinctAddress(String[] recipientEmailAddrs) {
        String tempStr = "";
        for (int i = 0; i < recipientEmailAddrs.length; i++) {
            if (!tempStr.contains(recipientEmailAddrs[i])) {
                tempStr += recipientEmailAddrs[i] + ",";
            }
        }
        tempStr = tempStr.substring(0, tempStr.length() - 1);
        return tempStr.split(",");
    }

    public static void jiami(String str){
        System.out.println(encriptMD5(str).toUpperCase());
    }
}
