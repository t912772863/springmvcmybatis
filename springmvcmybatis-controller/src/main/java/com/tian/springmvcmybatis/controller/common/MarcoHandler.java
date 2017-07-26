package com.tian.springmvcmybatis.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * 测试spring对webSocket的支持
 * Created by Administrator on 2017/7/25 0025.
 */
public class MarcoHandler extends AbstractWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(MarcoHandler.class);

    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage)throws Exception{
        logger.info("receive message: "+textMessage.getPayload());
        // 模拟延时
        Thread.sleep(2000);
        // 发送文本消息
        webSocketSession.sendMessage(new TextMessage("Polo!"));
    }

}
