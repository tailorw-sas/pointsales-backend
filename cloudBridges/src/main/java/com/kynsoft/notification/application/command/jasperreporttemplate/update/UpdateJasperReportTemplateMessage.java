package com.kynsoft.notification.application.command.jasperreporttemplate.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateJasperReportTemplateMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_JASPER_REPORT_TEMPLATE";

    public UpdateJasperReportTemplateMessage(UUID id) {
        this.id = id;
    }

}
