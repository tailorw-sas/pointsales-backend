package com.kynsoft.notification.application.command.file.deleteFileS3;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteFileS3Command implements ICommand {
    private boolean result;
    private final String url;

    public DeleteFileS3Command(String url) {
        this.url = url;
    }


    @Override
    public ICommandMessage getMessage() {
        return new DeleteFileS3Message(result);
    }
}
