package com.kynsof.calendar.application.command.receipt.delete;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReceiptDeleteCommand implements ICommand {

    private UUID id;

    @Override
    public ICommandMessage getMessage() {
        return new ReceiptDeleteMessage(id);
    }

}
