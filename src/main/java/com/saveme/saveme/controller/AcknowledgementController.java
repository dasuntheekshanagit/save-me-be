package com.saveme.saveme.controller;

import com.saveme.saveme.dto.AcknowledgementDto;
import com.saveme.saveme.model.Acknowledgement;
import com.saveme.saveme.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acknowledgements")
@RequiredArgsConstructor
@Tag(name = "Acknowledgements", description = "API for acknowledging notifications")
public class AcknowledgementController {

    private final NotificationService notificationService;

    @PostMapping
    @Operation(summary = "Acknowledge a notification")
    public ResponseEntity<Acknowledgement> acknowledgeNotification(@RequestBody AcknowledgementDto acknowledgementDto) {
        return ResponseEntity.ok(notificationService.acknowledgeNotification(acknowledgementDto));
    }
}
