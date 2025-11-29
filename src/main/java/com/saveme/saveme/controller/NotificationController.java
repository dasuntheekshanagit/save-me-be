package com.saveme.saveme.controller;

import com.saveme.saveme.dto.AcknowledgeRequestDto;
import com.saveme.saveme.dto.NotificationDto;
import com.saveme.saveme.dto.SummaryDto;
import com.saveme.saveme.model.Notification;
import com.saveme.saveme.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notifications", description = "Endpoints for creating and retrieving incident notifications.")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Create a new notification")
    public ResponseEntity<Notification> createNotification(
            @RequestPart("notification") NotificationDto notificationDto,
            @RequestPart(value = "photos", required = false) List<MultipartFile> photos) throws IOException {
        return ResponseEntity.ok(notificationService.createNotification(notificationDto, photos));
    }

    @GetMapping
    @Operation(summary = "Get all notifications")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @PostMapping("/{id}/vote-true")
    @Operation(summary = "Vote a notification as true")
    public ResponseEntity<Notification> voteTrue(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.voteTrue(id));
    }

    @PostMapping("/{id}/vote-spam")
    @Operation(summary = "Vote a notification as spam")
    public ResponseEntity<Notification> voteSpam(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.voteSpam(id));
    }

    @PostMapping("/{id}/acknowledge")
    @Operation(summary = "Acknowledge a notification with a phone number")
    public ResponseEntity<Notification> acknowledge(@PathVariable Long id, @RequestBody AcknowledgeRequestDto payload) {
        return ResponseEntity.ok(notificationService.acknowledge(id, payload.getPhoneNumber()));
    }

    @PostMapping("/{id}/close")
    @Operation(summary = "Close a notification")
    public ResponseEntity<Notification> closeNotification(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.closeNotification(id));
    }

    @GetMapping("/summary")
    @Operation(summary = "Get a summary of notifications")
    public ResponseEntity<SummaryDto> getSummary() {
        return ResponseEntity.ok(notificationService.getSummary());
    }
}
