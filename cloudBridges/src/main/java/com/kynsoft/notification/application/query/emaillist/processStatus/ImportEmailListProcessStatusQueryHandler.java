package com.kynsoft.notification.application.query.emaillist.processStatus;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsoft.notification.domain.service.ImportEmailListService;
import org.springframework.stereotype.Component;

@Component
public class ImportEmailListProcessStatusQueryHandler implements IQueryHandler<ImportEmailListProcessStatusQuery,ImportEmailListProcessStatusResponse> {
   private final ImportEmailListService importEmailListService;

    public ImportEmailListProcessStatusQueryHandler(ImportEmailListService importEmailListService) {
        this.importEmailListService = importEmailListService;
    }

    @Override
    public ImportEmailListProcessStatusResponse handle(ImportEmailListProcessStatusQuery query) {
        return importEmailListService.getImportEmailListProcessStatus(query.getRequest());
    }
}
