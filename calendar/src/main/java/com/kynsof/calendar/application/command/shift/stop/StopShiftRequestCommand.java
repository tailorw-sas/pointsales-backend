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
    private String service;
    private String doctor;

    public StopShiftRequestCommand(String local, String service, String doctor) {

        this.local = local;
        this.service = service;
        this.doctor = doctor;
    }

    public static StopShiftRequestCommand fromRequest(StopShiftRequest request) {
        return new StopShiftRequestCommand(request.getLocal(), request.getService(), request.getDoctor());
    }

    @Override
    public ICommandMessage getMessage() {
        return new StopShiftRequestMessage(id);
    }
}
