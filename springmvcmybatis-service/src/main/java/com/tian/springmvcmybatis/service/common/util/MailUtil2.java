package com.tian.springmvcmybatis.service.common.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class MailUtil2 {
    public static void main(String[] args) throws MessagingException {
// qqSender();
//        gmailSender();
        qqSender();
    }

    //gmail邮箱SSL方式
    private static void gmailssl(Properties props) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props.put("mail.debug", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
// props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.auth", "true");
    }


    //gmail邮箱的TLS方式
    private static void gmailtls(Properties props) {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    /*
    * 通过gmail邮箱发送邮件
    */
    public static void gmailSender() {

// Get a Properties object
        Properties props = new Properties();
//选择ssl方式
        gmailssl(props);

        final String username = "from@gmail.com";
        final String password = "password";
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


// -- Create a new message --
        Message msg = new MimeMessage(session);


// -- Set the FROM and TO fields --
        try {
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("to@qq.com"));
            msg.setSubject("Hello");
            msg.setText("How are you");
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (AddressException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println("Message sent.");
    }


    /*
    * 通过qq邮箱发送邮件,qq邮箱需要在设置里开启POP3/SMTP的授权，通过用户名+授权码方式才能发邮件
    */
    public static void qqSender() throws MessagingException {
        MailSSLSocketFactory msf = null;
        try {
            msf = new MailSSLSocketFactory();
            msf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
// TODO Auto-generated catch block
            e1.printStackTrace();
        }


        Properties props = new Properties();
// 开启调试
        props.setProperty("mail.debug", "true");
// 是否需要验证
        props.setProperty("mail.smtp.auth", "true");
// 发送邮件服务器
        props.setProperty("mail.smtp.host", "smtp.qq.com");
// 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", msf);


        String server = "smtp.163.com";
        String username = "";
        String password = "";




// 使用匿名内部类，用邮箱进行验证
        Session session = Session.getInstance(props, new Authenticator() {


            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
// 通过用户名和密码进行验证  授权码:hgiuwhddpzipbefj  密码: Tt912772863  yrbwwdsocaxfbcai
                return new PasswordAuthentication("912772863@qq.com","caiiouymvbokbeje");
            }


        });

//        String server2 = "smtp.163.com";
//        String username2 = "";
//        String password2 = "";
//        Transport transport = session.getTransport();
//        transport.connect(server2, username2, password2);
//        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
//        transport.close();


        Message message = new MimeMessage(session);
        try {
// 设置邮件发送方
            message.setFrom(new InternetAddress("912772863@qq.com"));
// 设置邮件标题
            message.setSubject("测试");
// 设置邮件内容
            message.setContent("测试", "text/html;charset=utf-8");
// 设置邮件接收方
            message.addRecipient(RecipientType.TO, new InternetAddress(
                    "294013436@qq.com"));


// 发送邮件
            Transport.send(message);


        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
