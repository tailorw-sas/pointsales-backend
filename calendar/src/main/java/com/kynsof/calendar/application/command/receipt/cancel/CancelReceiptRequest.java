package com.kynsof.calendar.application.command.receipt.cancel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CancelReceiptRequest {
    private UUID userId;
    private UUID receiptId;
}
