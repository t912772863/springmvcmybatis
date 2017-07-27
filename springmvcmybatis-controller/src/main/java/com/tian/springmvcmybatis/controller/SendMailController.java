package com.tian.springmvcmybatis.controller;

import com.tian.common.other.ResponseData;
import com.tian.springmvcmybatis.service.MailSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试邮件发送相关
 * Created by Administrator on 2017/7/27 0027.
 */
@Controller
@RequestMapping("mail")
public class SendMailController extends BaseController{
    @Value("${mail_sender_account}")
    private String mailSenderAccount;
    @Autowired
    private MailSenderImpl mailSender;

    /**
     * 发送普通的文本邮件
     * @param subject
     * @param message
     * @param receivers
     * @return
     */
    @RequestMapping("send_mail")
    @ResponseBody
    public ResponseData sendMail(String subject, String message, String receivers){
        String[] receiversArr = receivers.split(",");
        mailSender.sendMail(mailSenderAccount,subject,message,receiversArr);
        return success;
    }
}
