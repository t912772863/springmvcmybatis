package com.tian.springmvcmybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * 邮件发送业务层
 * Created by Administrator on 2017/7/27 0027.
 */
@Service
public class MailSenderImpl {
    @Autowired
    private MailSender javaMailSender;

    /**
     * 发送普通的文本邮件
     *
     * @param sender 发送人
     * @param subject 邮件主题
     * @param message 邮件文本内容
     * @param receiver 接收人(支持多个)
     */
    public void sendMail(String sender,String subject, String message, String ... receiver){
        // 构造消息
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置发送人
        simpleMailMessage.setFrom(sender);
        // 设置接收人
        simpleMailMessage.setTo(receiver);
        // 设置主题
        simpleMailMessage.setSubject(subject);
        // 设置文本消息
        simpleMailMessage.setText(message);
        // 发送
        javaMailSender.send(simpleMailMessage);
    }

}
