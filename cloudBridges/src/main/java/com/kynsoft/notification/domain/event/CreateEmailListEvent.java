package com.kynsoft.notification.domain.event;

import com.kynsoft.notification.domain.bean.ImportEmailListRow;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateEmailListEvent {
    private final List<ImportEmailListRow> importEmailListRow;
    private final String campaignId;

    public CreateEmailListEvent(List<ImportEmailListRow> importEmailListRow, String campaignId) {
        this.importEmailListRow = importEmailListRow;
        this.campaignId = campaignId;
    }
}
