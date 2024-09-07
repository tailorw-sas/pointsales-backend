package com.kynsoft.notification.application.command.emaillist.importEmailList;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.service.ImportEmailListService;
import org.springframework.stereotype.Component;

@Component
public class ImportEmailListCommandHandler implements ICommandHandler<ImportEmailListCommand> {
    private final ImportEmailListService importEmailListService;

    public ImportEmailListCommandHandler(ImportEmailListService importEmailListService) {
        this.importEmailListService = importEmailListService;
    }

    @Override
    public void handle(ImportEmailListCommand command) {
        importEmailListService.importEmailList(command.getImportEmailListRequest());
    }
}
