package com.kynsoft.notification.application.command.emaillist.importEmailList;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class ImportEmailListCommand implements ICommand {

    private final ImportEmailListRequest importEmailListRequest;

    public ImportEmailListCommand(ImportEmailListRequest importEmailListRequest) {
        this.importEmailListRequest = importEmailListRequest;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
