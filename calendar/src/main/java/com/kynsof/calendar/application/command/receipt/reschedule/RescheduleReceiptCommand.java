package com.kynsof.calendar.application.command.receipt.reschedule;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RescheduleReceiptCommand implements ICommand {

    private final UUID userId;
    private final UUID receiptId;
    private final UUID newScheduledId;
    private final String ipAddress;
    private final String userAgent;

    public RescheduleReceiptCommand(UUID receiptId, UUID userId, UUID newScheduledId, String ipAddress, String userAgent) {
        this.userId = userId;
        this.receiptId = receiptId;
        this.newScheduledId = newScheduledId;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    public static RescheduleReceiptCommand fromRequest(RescheduleReceiptRequest request, String ipAddress, String userAgent) {
        return new RescheduleReceiptCommand(request.getReceiptId(), request.getUserId(), request.getNewScheduledId(), ipAddress,
                userAgent );
    }

    @Override
    public ICommandMessage getMessage() {
        return new RescheduleReceiptMessage(receiptId);
    }
}
