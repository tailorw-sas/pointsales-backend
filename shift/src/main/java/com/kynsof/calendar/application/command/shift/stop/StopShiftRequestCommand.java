package com.kynsof.calendar.application.command.shift.stop;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class StopShiftRequestCommand implements ICommand {
    private UUID id;
    private String local;


    public StopShiftRequestCommand(String local) {

        this.local = local;

    }

    public static StopShiftRequestCommand fromRequest(StopShiftRequest request) {
        return new StopShiftRequestCommand(request.getLocal());
    }

    @Override
    public ICommandMessage getMessage() {
        return new StopShiftRequestMessage(id);
    }
}
