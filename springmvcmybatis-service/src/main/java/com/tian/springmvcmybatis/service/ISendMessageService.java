package com.tian.springmvcmybatis.service;

import com.tian.common.other.PageParam;
import com.tian.springmvcmybatis.dao.entity.SendMessage;

import java.util.List;
import java.util.Map;

/**
 * 发送消息相关业务层
 * Created by Administrator on 2017/1/18 0018.
 */
public interface ISendMessageService {
    boolean insertSendMessage(SendMessage sendMessage,String fileUrl);

    boolean deleteSendMessageById(Long id);

    boolean updateSendMessageById(SendMessage sendMessage);

    SendMessage querySendMessageById(Long id);

    PageParam<SendMessage> querySendMessageByPage(PageParam<SendMessage> pageParam);

    /**
     * 条件查询发送消息
     * @return
     */
    List<SendMessage> querySendMessageByRule(Map<String,Object> map);

}
