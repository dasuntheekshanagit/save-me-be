package com.saveme.saveme.controller;

import com.saveme.saveme.dto.AcknowledgementDto;
import com.saveme.saveme.dto.NotificationDto;
import com.saveme.saveme.dto.SummaryDto;
import com.saveme.saveme.model.Notification;
import com.saveme.saveme.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications", description = "API for managing notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @Operation(summary = "Create a new notification")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDto notificationDto) {
        return ResponseEntity.ok(notificationService.createNotification(notificationDto));
    }

    @GetMapping
    @Operation(summary = "Get all notifications")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @PostMapping("/acknowledge")
    @Operation(summary = "Acknowledge a notification")
    public ResponseEntity<?> acknowledgeNotification(@RequestBody AcknowledgementDto acknowledgementDto) {
        return ResponseEntity.ok(notificationService.acknowledgeNotification(acknowledgementDto));
    }

    @GetMapping("/summary")
    @Operation(summary = "Get a summary of notifications")
    public ResponseEntity<SummaryDto> getSummary() {
        return ResponseEntity.ok(notificationService.getSummary());
    }
}
