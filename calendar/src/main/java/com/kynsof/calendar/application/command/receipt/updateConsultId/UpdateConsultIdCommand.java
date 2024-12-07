package com.kynsof.calendar.application.command.receipt.updateConsultId;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateConsultIdCommand implements ICommand {

    private  Boolean result;
    private final UUID consultId;
    private final UUID receiptId;


    public UpdateConsultIdCommand(UUID consultId, UUID receiptId) {


        this.consultId = consultId;
        this.receiptId = receiptId;
    }

    public static UpdateConsultIdCommand fromRequest(UpdateConsultIdRequest request) {
        return new UpdateConsultIdCommand(request.getConsultId(), request.getReceiptId());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateConsultIdMessage(result);
    }
}
