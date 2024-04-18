package com.kynsoft.notification.application.command.jasperreporttemplate.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCreateJasperReportTemplateMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_JASPER_REPORT_TEMPLATE";

    public CreateCreateJasperReportTemplateMessage(UUID id) {
        this.id = id;
    }

}
