package com.tian.springmvcmybatis.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by Administrator on 2017/7/25 0025.
 */
@Configuration
// EnableWebSocketMessageBroker注解能够大WebSocket之上启用stomp
@EnableWebSocketMessageBroker
public class WebSocketMessageBroker extends AbstractWebSocketMessageBrokerConfigurer {
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/marcopolo").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/queue", "/topic","/user");
        registry.setApplicationDestinationPrefixes("/app");
        // 下面这个可以省略,因为默认值就是这个
        registry.setUserDestinationPrefix("/user/");
    }
}
