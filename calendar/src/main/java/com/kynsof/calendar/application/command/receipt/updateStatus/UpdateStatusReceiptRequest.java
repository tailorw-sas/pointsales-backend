package com.kynsof.calendar.application.command.receipt.updateStatus;

import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusReceiptRequest {
    private EStatusReceipt statusReceipt;

}
