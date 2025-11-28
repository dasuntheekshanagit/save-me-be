package com.saveme.saveme.controller;

import com.saveme.saveme.dto.NotificationDto;
import com.saveme.saveme.dto.SummaryDto;
import com.saveme.saveme.model.Notification;
import com.saveme.saveme.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notifications", description = "API for managing notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Create a new notification with optional photos")
    public ResponseEntity<Notification> createNotification(@RequestPart("notification") NotificationDto notificationDto,
                                                           @RequestPart(value = "photos", required = false) List<MultipartFile> photos) throws IOException {
        return ResponseEntity.ok(notificationService.createNotification(notificationDto, photos));
    }

    @GetMapping
    @Operation(summary = "Get all notifications")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/summary")
    @Operation(summary = "Get a summary of notifications")
    public ResponseEntity<SummaryDto> getSummary() {
        return ResponseEntity.ok(notificationService.getSummary());
    }
}
