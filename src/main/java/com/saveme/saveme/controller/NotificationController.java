package com.saveme.saveme.controller;

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
    @Operation(summary = "Create a new notification",
            description = "Creates a new incident notification. This endpoint accepts multipart/form-data, allowing you to send both the notification details (as a JSON object) and multiple photo files in a single request.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Notification created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Notification.class)))
            })
    public ResponseEntity<Notification> createNotification(
            @RequestPart("notification") @Schema(example =
                    "{\n" +
                    "  \"reporterName\": \"John Doe\",\n" +
                    "  \"telephoneNumbers\": [\"+15551234567\", \"+15557654321\"],\n" +
                    "  \"latitude\": 6.9271,\n" +
                    "  \"longitude\": 79.8612,\n" +
                    "  \"severity\": \"CRITICAL\",\n" +
                    "  \"isRoadBlocked\": true,\n" +
                    "  \"location\": \"Galle Road, Colombo\",\n" +
                    "  \"affectedPeople\": 10,\n" +
                    "  \"affectedChildren\": 2,\n" +
                    "  \"affectedYoung\": 3,\n" +
                    "  \"affectedAdults\": 5,\n" +
                    "  \"comments\": \"Heavy flooding, water is rising quickly.\",\n" +
                    "  \"waterLevel\": 2.5,\n" +
                    "  \"type\": \"flood\"\n" +
                    "}") NotificationDto notificationDto,
            @RequestPart(value = "photos", required = false) List<MultipartFile> photos) throws IOException {
        return ResponseEntity.ok(notificationService.createNotification(notificationDto, photos));
    }

    @GetMapping
    @Operation(summary = "Get all notifications",
            description = "Retrieves a list of all submitted incident notifications.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved notifications",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Notification.class)))
            })
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/summary")
    @Operation(summary = "Get a summary of notifications",
            description = "Provides a quick summary of notification statistics.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved summary",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SummaryDto.class),
                                    examples = @ExampleObject(value = "{\"tapped\": 15, \"missing\": 5, \"criticalAlerts\": 3}")))
            })
    public ResponseEntity<SummaryDto> getSummary() {
        return ResponseEntity.ok(notificationService.getSummary());
    }
}
