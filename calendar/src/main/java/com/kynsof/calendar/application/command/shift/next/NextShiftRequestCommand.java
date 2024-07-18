package com.kynsof.calendar.application.command.shift.next;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class NextShiftRequestCommand implements ICommand {

    private String local;
    private String service;
    private String doctor;
    private String lastShift;

    public static NextShiftRequestCommand fromRequest(NextShiftRequest request) {
        return new NextShiftRequestCommand(request.getLocal(), request.getService(), request.getDoctor(), request.getLastShift());
    }

    @Override
    public ICommandMessage getMessage() {
        return new NextShiftRequestMessage(UUID.randomUUID());
    }
}
