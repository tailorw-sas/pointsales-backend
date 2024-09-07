package com.kynsoft.notification.domain.event;

import com.kynsoft.notification.domain.bean.ImportEmailListRow;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateEmailListEvent {
    private final List<ImportEmailListRow> importEmailListRow;

    public CreateEmailListEvent(List<ImportEmailListRow> importEmailListRow) {
        this.importEmailListRow = importEmailListRow;
    }
}
