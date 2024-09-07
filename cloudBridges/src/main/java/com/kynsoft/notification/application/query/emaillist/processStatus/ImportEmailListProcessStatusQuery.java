package com.kynsoft.notification.application.query.emaillist.processStatus;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

@Getter
public class ImportEmailListProcessStatusQuery implements IQuery {
    private final ImportEmailListProcessStatusRequest request;

    public ImportEmailListProcessStatusQuery(ImportEmailListProcessStatusRequest request) {
        this.request = request;
    }
}
