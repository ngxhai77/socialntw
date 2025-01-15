package com.example.socialntw.service;

import com.example.socialntw.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendNotification(NotificationDto notificationDto) {
        messagingTemplate.convertAndSendToUser(notificationDto.getUserId().toString(), "/topic/notifications", notificationDto);
    }
}
