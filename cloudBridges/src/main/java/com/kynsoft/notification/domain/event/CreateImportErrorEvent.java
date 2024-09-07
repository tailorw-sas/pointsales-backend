package com.kynsoft.notification.domain.event;

import com.kynsoft.notification.infrastructure.entity.redis.ImportEmailListErrorRow;
import lombok.Getter;

@Getter
public class CreateImportErrorEvent {
    private final ImportEmailListErrorRow importEmailListErrorRow;

    public CreateImportErrorEvent(ImportEmailListErrorRow importEmailListErrorRow) {
        this.importEmailListErrorRow = importEmailListErrorRow;
    }
}
