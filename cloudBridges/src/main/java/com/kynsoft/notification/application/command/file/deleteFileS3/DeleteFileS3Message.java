package com.kynsoft.notification.application.command.file.deleteFileS3;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class DeleteFileS3Message implements ICommandMessage {


    private final boolean result;




    public DeleteFileS3Message(boolean result) {
        this.result = result;
    }

}
