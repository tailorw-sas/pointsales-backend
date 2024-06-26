package com.kynsof.calendar.application.command.receipt.updateStatus;

import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateStatusReceiptRequest {
    private EStatusReceipt statusReceipt;

}
