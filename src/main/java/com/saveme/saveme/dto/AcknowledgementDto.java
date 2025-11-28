package com.saveme.saveme.dto;

import lombok.Data;

@Data
public class AcknowledgementDto {
    private Long incidentId;
    private String phoneNumber;
    private boolean isTrue;
    private boolean isSpam;
}
