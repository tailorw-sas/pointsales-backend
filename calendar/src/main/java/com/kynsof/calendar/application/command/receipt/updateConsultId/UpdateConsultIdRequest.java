package com.kynsof.calendar.application.command.receipt.updateConsultId;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateConsultIdRequest {
    private UUID consultId;
    private UUID receiptId;

}
