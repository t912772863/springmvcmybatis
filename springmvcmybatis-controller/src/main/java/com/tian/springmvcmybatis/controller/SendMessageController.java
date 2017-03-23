package com.tian.springmvcmybatis.controller;

import com.tian.common.other.PageParam;
import com.tian.common.other.ResponseData;
import com.tian.springmvcmybatis.dao.entity.SendMessage;
import com.tian.springmvcmybatis.service.ISendMessageService;
import com.tian.springmvcmybatis.service.common.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 发送消息控制层
 * Created by Administrator on 2017/1/18 0018.
 */
@Controller
@RequestMapping("send_message")
public class SendMessageController extends BaseController{
    @Autowired
    private ISendMessageService sendMessageService;

    /**
     * 新增一条发送消息记录
     * @param sendMessage
     * @return
     */
    @RequestMapping("insert_send_message")
    @ResponseBody
    public ResponseData insertSendMessage(SendMessage sendMessage, String fileUrl){
        // TODO: 2017/1/18 0018  默认发送时间就为当前时间
        sendMessage.setSendTime(new Date());
        sendMessageService.insertSendMessage(sendMessage,fileUrl);
        return success;
    }

    /**
     * 分页查询发送消息
     * @param pageParam
     * @param request
     * @return
     */
    @RequestMapping("query_send_message_by_page")
    @ResponseBody
    public ResponseData querySendMessageByPage(PageParam<SendMessage> pageParam, HttpServletRequest request){
        pageParam.getParams().put("startTime",request.getParameter("startTime"));
        pageParam.getParams().put("endTime",request.getParameter("endTime"));
        pageParam.getParams().put("status",request.getParameter("status"));
        pageParam.getParams().put("title",request.getParameter("title"));

        sendMessageService.querySendMessageByPage(pageParam);
        return successData.setData(pageParam);
    }

    /**
     * 消息正在发送的过程中,暂停,恢复发送功能
     * @return
     */
    @RequestMapping("pause_send_message")
    @ResponseBody
    public ResponseData pauseSendMessage(Long id,String state){
        boolean index = false;
        if("PAUSE".equals(state)){
            index = false;
        }else if("DO".equals(state)){
            index = true;
        }
        // 设置状态
        TimerTask.switchMap.put("sendMessage_"+id,new Boolean(index));
        // 执行任务
        TimerTask.sendMessageMap.get("sendMessage_"+id).run();
        return success;
    }

}
