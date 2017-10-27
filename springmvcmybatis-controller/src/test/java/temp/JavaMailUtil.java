package temp;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
public class JavaMailUtil {
    /**
     * 配置发件人邮箱
     */
    public static String MY_EMAIL_ACCOUNT = "912772863@qq.com";
    /**
     * 发件人邮箱的密码, 或者授权码
     */
    public static String MY_EMAIL_PASSWORD = "bnkempwfpdhibfee";

    /**
     * 发件人邮箱类型所对应的邮件服务host
     */
    public static String MY_EMAIL_SMTP_HOST = "smtp.qq.com";
    private Session session;

    private static JavaMailUtil javaMail = new JavaMailUtil();
    private JavaMailUtil(){}

    public static JavaMailUtil getInstance(){
        return javaMail;
    }


    public static void main(String[] args) throws Exception{
        JavaMailUtil javaMail = JavaMailUtil.getInstance();
        javaMail.sendMail("TianXiong",
                "13510272496@139.com","我的139","测试网络图片的邮件","这是一张图片<br/><img src='//www.baidu.com/img/bd_logo1.png'/>",
                "C:\\Users\\Administrator\\Desktop\\临时文件夹\\虫虫大作战-视频.MP4");
    }

    /**
     * 发送邮件, 不带附件
     * @param sendName 发件人名称
     * @param receiveMail 收件人邮箱
     * @param receiveName 收件人名称
     * @param mailTitle 邮件标题
     * @param mailContext 邮件内容
     * @throws Exception
     */
    public void sendMail(String sendName, String receiveMail, String receiveName,
                                String mailTitle, String mailContext) throws Exception{
        // 获取会话
        Session session = getSession();
        // 创建邮件内容
        MimeMessage message = createSimpleMessage(session, MY_EMAIL_ACCOUNT, sendName,
                receiveMail,receiveName,mailTitle,mailContext);
        // 发送
        doSend(session, message);
    }


    /**
     * 发送邮件 , 带有附件
     * @param sendName 发件人名称
     * @param receiveMail 收件人邮箱
     * @param receiveName 收件人名称
     * @param mailTitle 邮件标题
     * @param mailContext 邮件内容
     * @param filePath 附件路径
     * @throws Exception
     */
    public void sendMail(String sendName, String receiveMail, String receiveName,
                                 String mailTitle, String mailContext, String filePath) throws Exception{
        // 获取会话
        Session session = getSession();
        // 创建邮件内容
        MimeMessage message = createMimeMessage2(session, MY_EMAIL_ACCOUNT, sendName,
                receiveMail,receiveName,mailTitle,mailContext,filePath);
        // 发送
        doSend(session, message);
    }

    /**
     * 获取邮件服务器会话
     * 无参,则默认用工具类中配置的 MY_EMAIL_SMTP_HOST
     * @return
     */
    private synchronized Session getSession(){
        if(this.session == null){
            this.session = getSession(MY_EMAIL_SMTP_HOST);
        }
        return this.session;
    }

    /**
     * 获取邮件服务器会话
     * 显示的指定 emailSmtpHost(如:smtp.163.com)
     * @param emailSmtpHost
     * @return
     */
    private static Session getSession(String emailSmtpHost){
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", emailSmtpHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。

        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);


        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        return session;
    }

    /**
     * 通过session把内容下发
     * @param session
     * @param message
     * @throws Exception
     */
    private static void doSend(Session session, MimeMessage message) throws Exception {
        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
        transport.connect(MY_EMAIL_ACCOUNT, MY_EMAIL_PASSWORD);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }

    /**
     * 创建一个还有文本内容
     * @param session 与邮件服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param sendName 发件人名称
     * @param receiveMail 收件人邮箱
     * @param receiveName 收件人名称
     * @param mailTitle 邮件标题
     * @param mailContext 邮件内容(可以是纯文本, 也可以是包含html标签的内容,邮箱会自动渲染)
     * @return
     * @throws Exception
     */
    private static MimeMessage createSimpleMessage(Session session, String sendMail,String sendName, String receiveMail, String receiveName,
            String mailTitle, String mailContext) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, sendName, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveName, "UTF-8"));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(mailTitle, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(mailContext,"text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

    /**
     * 创建一个还有文本内容, 图片, 附件的邮件, 该邮件中的图片是以节点的形式拼在邮件内容中的,需要注意节点名,与添加位置的关系布局等
     * 功能上可以实现, 但是局限性比较多, 建议在发送包含有图片的邮件的时候,创建链接形式的图片内容, 这样可以整体当成一个纯文本, 不
     * 用关心里面的细节.
     * @param session 与邮件服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param sendName 发件人名称
     * @param receiveMail 收件人邮箱
     * @param receiveName 收件人名称
     * @param mailTitle 邮件标题
     * @param mailContext 邮件内容(可以是纯文本, 也可以是包含html标签的内容,邮箱会自动渲染)
     * @return
     * @throws Exception
     */
    @Deprecated
    private static MimeMessage createMimeMessage(Session session, String sendMail,String sendName, String receiveMail, String receiveName,
                                                String mailTitle, String mailContext) throws Exception {
        // 1. 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, sendName, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail, receiveName, "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(mailTitle, "UTF-8");

        /*
         * 下面是邮件内容的创建:
         */

        // 5. 创建图片“节点”
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("C:\\Users\\Administrator\\Desktop\\临时文件夹\\javaFather.jpg")); // 读取本地文件
        image.setDataHandler(dh);                   // 将图片数据添加到“节点”
        image.setContentID("image_fairy_tail");     // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）

        // 6. 创建文本“节点”
        MimeBodyPart text = new MimeBodyPart();
        //    这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        text.setContent("这是一张图片<br/><img src='cid:image_fairy_tail'/>", "text/html;charset=UTF-8");

        // 7. （文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.addBodyPart(image);
        mm_text_image.setSubType("related");    // 关联关系

        // 8. 将 文本+图片 的混合“节点”封装成一个普通“节点”
        //    最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        //    上面的 mm_text_image 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);

        // 9. 创建附件“节点”
        MimeBodyPart attachment = new MimeBodyPart();
        DataHandler dh2 = new DataHandler(new FileDataSource("C:\\Users\\Administrator\\Desktop\\临时文件夹\\虫虫大作战-视频.MP4"));  // 读取本地文件
        attachment.setDataHandler(dh2);                                             // 将附件数据添加到“节点”
        attachment.setFileName(MimeUtility.encodeText(dh2.getName()));              // 设置附件的文件名（需要编码）

        // 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合“节点” / Multipart ）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);
        mm.addBodyPart(attachment);     // 如果有多个附件，可以创建多个多次添加
        mm.setSubType("mixed");         // 混合关系

        // 11. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
        message.setContent(mm);

        // 12. 设置发件时间
        message.setSentDate(new Date());

        // 13. 保存上面的所有设置
        message.saveChanges();

        return message;
    }

    /**
     * 创建一个还有文本内容, 附件的邮件
     * @param session 与邮件服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param sendName 发件人名称
     * @param receiveMail 收件人邮箱
     * @param receiveName 收件人名称
     * @param mailTitle 邮件标题
     * @param mailContext 邮件内容(可以是纯文本, 也可以是包含html标签的内容,邮箱会自动渲染)
     * @param filePath 要上传的附件的物理路径
     * @return
     * @throws Exception
     */
    private static MimeMessage createMimeMessage2(Session session, String sendMail,String sendName, String receiveMail, String receiveName,
                                                 String mailTitle, String mailContext, String filePath) throws Exception {
        // 1. 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, sendName, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail, receiveName, "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(mailTitle, "UTF-8");

        /*
         * 下面是邮件内容的创建:
         */


        // 6. 创建文本“节点”
        MimeBodyPart text = new MimeBodyPart();
        //    这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        text.setContent(mailContext, "text/html;charset=UTF-8");

        // 7. （文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.setSubType("related");    // 关联关系

        // 8. 将 文本+图片 的混合“节点”封装成一个普通“节点”
        //    最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        //    上面的 mm_text_image 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);

        // 9. 创建附件“节点”
        MimeBodyPart attachment = new MimeBodyPart();
        DataHandler dh2 = new DataHandler(new FileDataSource(filePath));  // 读取本地文件
        attachment.setDataHandler(dh2);                                             // 将附件数据添加到“节点”
        attachment.setFileName(MimeUtility.encodeText(dh2.getName()));              // 设置附件的文件名（需要编码）

        // 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合“节点” / Multipart ）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);
        mm.addBodyPart(attachment);     // 如果有多个附件，可以创建多个多次添加
        mm.setSubType("mixed");         // 混合关系

        // 11. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
        message.setContent(mm);

        // 12. 设置发件时间
        message.setSentDate(new Date());

        // 13. 保存上面的所有设置
        message.saveChanges();

        return message;
    }


}
