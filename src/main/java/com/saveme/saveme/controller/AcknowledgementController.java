package com.saveme.saveme.controller;

import com.saveme.saveme.dto.AcknowledgementDto;
import com.saveme.saveme.model.Acknowledgement;
import com.saveme.saveme.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acknowledgements")
@Tag(name = "Acknowledgements", description = "Endpoint for acknowledging an existing notification.")
public class AcknowledgementController {

    private final NotificationService notificationService;

    public AcknowledgementController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    @Operation(summary = "Acknowledge a notification",
            description = "Allows a user to acknowledge an existing notification, marking it as either true or spam. This helps verify the incident.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Acknowledgement details",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AcknowledgementDto.class),
                            examples = @ExampleObject(value = "{\"incidentId\": 1, \"phoneNumber\": \"+15558889999\", \"isTrue\": true, \"isSpam\": false}")
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Acknowledgement successful",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Acknowledgement.class)))
            })
    public ResponseEntity<Acknowledgement> acknowledgeNotification(@RequestBody AcknowledgementDto acknowledgementDto) {
        return ResponseEntity.ok(notificationService.acknowledgeNotification(acknowledgementDto));
    }
}
