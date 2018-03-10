package temp;

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
public class GuanXinSendMailTest {
    public static void sendMail2(String _receiverMail) throws UnsupportedEncodingException {
        String url = "";
        //接口版本号, 写死
        String version = "0200";
        // 省份, 写死
        String province = "cn";
        // 来源, 固定写死
        String comeFrom = "999";
        // 命令ID, 写死
        String commandId = "CMD00001";
        //发件人邮箱
        String from = "";
        // 收件人地址
        String to = "912772863@qq.com";
        // 业务编码, 139给写死
        String busiCode = "richinfoBusiCode";
        // key 写死
        String key = "key";
        // 模版ID任意传一个就行, 用不上
        String templateId = "templateId";
        // 流水号,32位不重复就可以了
        String reqsn = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"00000"+System.currentTimeMillis();
        // 2.KEY=Md5(COMEFROM + COMMANDID + FROM + TO + BUSICODE + TEMPLATEID + KEY)
        String skey = encriptMD5(comeFrom+commandId+from+to+busiCode+templateId+key);
        // 请求时间
        String reqTime = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date());
        // 发件人昵称
        String fromName = "";
        // 收件人昵称, 非必传
        String toName = "";
        // 品牌名
        String brand = "";
        // 邮件标题
        String title = "139车主服务提示";
        // 邮箱内容
        String context = "【中国移动 139邮箱】139车主服务：您的车辆粤A12345于2017年6月3日有新违章，为免影响用车，请速处理！详情请登录“139车主服务”查看，已办请忽略。";




        String testXML = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +               //xml头
                "<EMAIL>\n" +
                "  <HEAD>\n" +
                "    <VERSION>"+version+"</VERSION>\n" +                                     //接口版本号, 139平台定义
                "    <PROVINCE>"+province+"</PROVINCE>\n" +                                     // 固定写死
                "    <COMEFROM>"+comeFrom+"</COMEFROM>\n" +
                "    <COMMANDID>"+commandId+"</COMMANDID>\n" +                             // 固定写死
                "    <SKEY>"+skey+"</SKEY>\n" +
                "    <REQSN>"+reqsn+"</REQSN>\n" +    //唯一
                "    <REQTIME>"+reqTime+"</REQTIME>\n" +
                "  </HEAD>\n" +
                "  <BODY>\n" +
                "    <FROM>"+from+"</FROM>\n" +
                "    <FROMNAME>"+fromName+"</FROMNAME>\n" +
                "    <TO>"+to+"</TO>\n" +
                "    <TONAME>"+toName+"</TONAME>\n" +
                "    <BRAND>"+brand+"</BRAND>\n" +
                "    <BUSICODE>"+busiCode+"</BUSICODE>\n" +
                "    <TEMPLATEID>"+templateId+"</TEMPLATEID>\n" +
                "    <TITLE>"+title+"</TITLE>\n" +
                "    <INFO>\n" +
                "      <ITEM>\n" +
                "        <ID>contextParam</ID>\n" +
                "        <TYPE>text/plain</TYPE>\n" +
                "        <VALUE><![CDATA["+context+"]]></VALUE>\n" +
                "      </ITEM>\n" +
                "    </INFO >\n" +
                "  </BODY>\n" +
                "</EMAIL>";

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
        sendMail2("13510272496@139.com");
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
