package com.tian.springmvcmybatis.service.config;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Properties;

/**
 * 一些常用的配置,可以用来替换xml配置文件中的绝大多数内容,也可以并存
 * Created by Administrator on 2017/7/27 0027.
 */
@Configuration
public class CommonConfig {
    /**
     * 注入java发送邮件的工具类
     * @return
     */
    @Bean
    public MailSender mailSender(){
        Properties prop = new Properties();
        try {
            // 读取配置文件
            prop.load(CommonConfig.class.getResourceAsStream("/business.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);

        javaMailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.setProperty("mail.smtp.port", "465");
        javaMailProperties.setProperty("mail.smtp.socketFactory.port", "465");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // 默认为java底层会话主机, 这里可以自定义
        mailSender.setHost(prop.get("mail_host").toString());
        // 默认为25端口
        mailSender.setPort(Integer.parseInt(prop.get("mail_port").toString()));
        // 如果邮件服务器需要认证,则还需要用户名和密码
        mailSender.setUsername(prop.get("mail_sender_account").toString());
        // QQ邮箱开启保护不能用密码登录,所以这里用授权码
        mailSender.setPassword(prop.get("mail_password").toString());
        //
        mailSender.setJavaMailProperties(javaMailProperties);
        try{
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);


        }catch (Exception e){
            e.printStackTrace();
        }
        return mailSender;
    }
}
