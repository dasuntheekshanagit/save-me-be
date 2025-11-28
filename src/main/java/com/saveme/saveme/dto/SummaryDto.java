package com.saveme.saveme.dto;

import lombok.Data;

@Data
public class SummaryDto {
    private long tapped;
    private long missing;
    private long criticalAlerts;
}
