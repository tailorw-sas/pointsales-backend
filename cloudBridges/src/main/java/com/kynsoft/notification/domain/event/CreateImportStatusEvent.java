package com.kynsoft.notification.domain.event;

import com.kynsoft.notification.infrastructure.entity.redis.ImportEmailListProcessStatus;
import lombok.Getter;

@Getter
public class CreateImportStatusEvent {

    private final ImportEmailListProcessStatus processStatus;

    public CreateImportStatusEvent(ImportEmailListProcessStatus processStatus) {
        this.processStatus = processStatus;
    }
}
