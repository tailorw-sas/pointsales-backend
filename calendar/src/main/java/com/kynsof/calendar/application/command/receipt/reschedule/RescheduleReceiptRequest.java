package com.kynsof.calendar.application.command.receipt.reschedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RescheduleReceiptRequest {
    private UUID userId;
    private UUID receiptId;
    private UUID newScheduledId;
}
