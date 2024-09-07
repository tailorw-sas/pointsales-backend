package com.kynsoft.notification.application.query.emaillist.processStatus;

import lombok.Getter;

@Getter
public class ImportEmailListProcessStatusRequest {
    private final String importProcessId;

    public ImportEmailListProcessStatusRequest(String importProcessId) {
        this.importProcessId = importProcessId;
    }
}
