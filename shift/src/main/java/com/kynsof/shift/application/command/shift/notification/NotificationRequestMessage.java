package com.kynsof.shift.application.command.shift.notification;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class NotificationRequestMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "SHIFT_NOTIFICATION_REQUEST";

    public NotificationRequestMessage(boolean result) {
        this.result = result;
    }

}
