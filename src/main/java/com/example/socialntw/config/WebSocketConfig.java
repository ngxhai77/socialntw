package com.example.socialntw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/user");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

   // Frontend implementation:
//const socket = new SockJS('/ws');
//const stompClient = Stomp.over(socket);
//
//stompClient.connect({}, function (frame) {
//        console.log('Connected: ' + frame);
//        stompClient.subscribe('/user/topic/notifications', function (notification) {
//            showNotification(JSON.parse(notification.body));
//        });
//    });
//
//    function showNotification(notification) {
//        alert('New notification: ' + notification.message);
//    }
}