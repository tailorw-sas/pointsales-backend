package com.kynsof.shift.application.command.shift.notification;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationShiftRequestCommand implements ICommand {
    private boolean result;
    private final String local;
    private final String service;
    private final String shift;

    public NotificationShiftRequestCommand(String local, String service, String shift) {
        this.local = local;
        this.service = service;
        this.shift = shift;
    }

    public static NotificationShiftRequestCommand fromRequest(NotificationRequest request) {
        return new NotificationShiftRequestCommand(request.getLocal(), request.getService(), request.getShift());
    }

    @Override
    public ICommandMessage getMessage() {
        return new NotificationRequestMessage(result);
    }
}
