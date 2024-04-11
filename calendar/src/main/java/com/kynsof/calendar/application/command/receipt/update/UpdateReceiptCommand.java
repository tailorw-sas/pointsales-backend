package com.kynsof.calendar.application.command.receipt.update;

import com.kynsof.calendar.application.command.receipt.reschedule.RescheduleReceiptMessage;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateReceiptCommand implements ICommand {

    private final String requestId ;
    private final String Status;


    public UpdateReceiptCommand(String requestId, String status) {

        this.requestId = requestId;
        Status = status;
    }



    @Override
    public ICommandMessage getMessage() {
        return new RescheduleReceiptMessage(UUID.randomUUID());
    }
}
