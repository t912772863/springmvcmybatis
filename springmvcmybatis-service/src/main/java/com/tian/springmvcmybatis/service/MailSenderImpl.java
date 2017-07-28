package com.tian.springmvcmybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * 邮件发送业务层
 * Created by Administrator on 2017/7/27 0027.
 */
@Service
public class MailSenderImpl {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送普通的文本邮件
     *
     * @param sender   发送人
     * @param subject  邮件主题
     * @param message  邮件文本内容
     * @param receiver 接收人(支持多个)
     */
    public void sendTextMail(String sender, String subject, String message, String... receiver) {
        // 构造消息
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置发送人,也就是发送人的帐号, 这个一定要和授权登录的帐号一致
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

    /**
     * 发送带有附件的邮件
     * @param sender 发邮件的帐号
     * @param subject 邮件主题
     * @param message 邮件内容
     * @param filePath 附件的路径(包括文件名)
     * @param fileName 上传文件后的名字
     * @param receiver 接收人
     * @throws Exception
     */
    public void sendMultipartMail(String sender, String subject, String message, String filePath,String fileName,String... receiver) throws Exception {
        // 创建一个富文本类型的消息体
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 创建一个spring封装的简化处理邮件相关内容的对象, 这里的true表示这是一个multipart类型的消息
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        // 组装邮件相关内容
        messageHelper.setFrom(sender);
        messageHelper.setTo(receiver);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        // 添加附件
        FileSystemResource file = new FileSystemResource(filePath);
        // 上传的文件重命名
        fileName = fileName == null?file.getFilename():fileName;
        // 文件名, 文件对象
        messageHelper.addAttachment(fileName,file);
        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送带有附件的邮件
     * @param sender
     * @param subject
     * @param message
     * @param filePath
     * @param receiver
     * @throws Exception
     */
    public void sendMultipartMail(String sender, String subject, String message, String filePath,String... receiver) throws Exception {
        sendMultipartMail(sender,subject,message,filePath,null,receiver);
    }




}
