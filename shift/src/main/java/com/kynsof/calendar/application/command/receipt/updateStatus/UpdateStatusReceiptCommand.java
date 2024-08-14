package com.kynsof.calendar.application.command.receipt.updateStatus;

import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateStatusReceiptCommand implements ICommand {

    private final UUID id ;
    private final EStatusReceipt Status;


    public UpdateStatusReceiptCommand(UUID id, EStatusReceipt status) {

        this.id = id;
        Status = status;
    }

    public static UpdateStatusReceiptCommand fromRequest(UUID id, UpdateStatusReceiptRequest request) {
        return new UpdateStatusReceiptCommand(id, request.getStatusReceipt());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateStatusReceiptMessage(id);
    }
}
