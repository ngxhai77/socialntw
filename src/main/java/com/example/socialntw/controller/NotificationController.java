package com.example.socialntw.controller;

import com.example.socialntw.dto.NotificationDto;
import com.example.socialntw.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendNotification(@RequestBody NotificationDto notificationDto) {
        notificationService.sendNotification(notificationDto);
        return ResponseEntity.ok().build();
    }
}