package com.kynsof.calendar.application.command.receipt.confirmPayment;

import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ConfirmPaymentReceiptCommand implements ICommand {

    private final UUID receiptId;
    private final UUID userId;
    private final UUID scheduleId;
    private final UUID serviceId;
    private final EStatusReceipt status;
    private final String requestId;
    private final String authorizationCode;
    private final String sessionId;
    private final String reference;
    public ConfirmPaymentReceiptCommand(UUID receiptId, UUID userId, UUID scheduleId, UUID serviceId,
                                        EStatusReceipt status, String requestId, String authorizationCode,
                                        String sessionId, String reference) {


        this.receiptId = receiptId;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.serviceId = serviceId;
        this.status = status;
        this.requestId = requestId;
        this.authorizationCode = authorizationCode;
        this.sessionId = sessionId;
        this.reference = reference;
    }

    public static ConfirmPaymentReceiptCommand fromRequest(UUID receiptId, ConfirmPaymentReceiptRequest request) {
        return new ConfirmPaymentReceiptCommand(receiptId, request.getUserId(), request.getScheduleId(),
                request.getServiceId(), request.getStatus(),request.getRequestId() ,request.getAuthorizationCode() ,
                request.getSessionId(), request.getReference());
    }

    @Override
    public ICommandMessage getMessage() {
        return new ConfirmPaymentReceiptMessage(receiptId);
    }
}
