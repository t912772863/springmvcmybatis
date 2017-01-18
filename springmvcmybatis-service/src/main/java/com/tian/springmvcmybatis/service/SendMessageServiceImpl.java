package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.common.PageParam;
import com.tian.springmvcmybatis.dao.entity.File;
import com.tian.springmvcmybatis.dao.entity.SendMessage;
import com.tian.springmvcmybatis.dao.mapper.SendMessageMapper;
import com.tian.springmvcmybatis.service.common.InnerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/18 0018.
 */
@Service
public class SendMessageServiceImpl implements ISendMessageService {
    @Autowired
    private SendMessageMapper sendMessageMapper;
    @Autowired
    private IFileService fileService;

    @Transactional
    public boolean insertSendMessage(SendMessage sendMessage,String fileUrl) {
        sendMessage.setCreateTime(new Date());
        sendMessage.setStatus(InnerConstant.DATA_STATUS_COMMON);
        // 保存发送消息记录
        sendMessageMapper.insertSelective(sendMessage);
        // 保存发送消息关联的文件记录
        File file = new File();
        file.setFileType(InnerConstant.FILE_TYPE_DOCUMENT);
        file.setDataId(sendMessage.getId());
        file.setTableName("send_message");
        file.setUrl(fileUrl);
        fileService.insertFile(file);
        return true;
    }

    @Transactional
    public boolean deleteSendMessageById(Long id) {
        sendMessageMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Transactional
    public boolean updateSendMessageById(SendMessage sendMessage) {
        sendMessage.setUpdateTime(new Date());
        sendMessageMapper.updateByPrimaryKeySelective(sendMessage);
        return true;
    }

    public SendMessage querySendMessageById(Long id) {
        return sendMessageMapper.selectByPrimaryKey(id);
    }

    public PageParam<SendMessage> querySendMessageByPage(PageParam<SendMessage> pageParam) {
        List<SendMessage> list = sendMessageMapper.queryByPage(pageParam);
        pageParam.setResult(list);
        return pageParam;
    }

    public List<SendMessage> querySendMessageByRule(Map<String, Object> map) {
        List<SendMessage> list = sendMessageMapper.queryByRule(map);
        if(list == null){
            return new ArrayList<SendMessage>();
        }
        return list;
    }
}
