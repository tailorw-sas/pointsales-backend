package com.kynsoft.notification.application.query.emaillist.importError;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

@Getter
public class ImportEmailListErrorQuery implements IQuery {
    private final ImportEmailListErrorRequest request;

    public ImportEmailListErrorQuery(ImportEmailListErrorRequest request) {
        this.request = request;
    }
}
