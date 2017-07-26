//package com.tian.springmvcmybatis.controller.config;
//
//import com.tian.springmvcmybatis.controller.common.MarcoHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
///**
// * webSocket相关的java配置
// * 在java配置中, 启用WebSocket并映射消息处理器

// * 后面与spring整合了这个就不单独启用了
// * Created by Administrator on 2017/7/25 0025.
// */
//@Configuration
//// 启用WebSocket功能
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer{
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        // 将MarcoHandler映射到/marco
//        registry.addHandler(marcoHandler(),"/marco").withSockJS();
//    }
//
//    /**
//     * 声明bean
//     * @return
//     */
//    @Bean
//    public MarcoHandler marcoHandler(){
//        return new MarcoHandler();
//    }
//}
