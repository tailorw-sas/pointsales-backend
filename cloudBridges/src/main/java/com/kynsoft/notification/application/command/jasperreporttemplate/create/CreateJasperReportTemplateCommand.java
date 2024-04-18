package com.kynsoft.notification.application.command.jasperreporttemplate.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsoft.notification.domain.dto.JasperReportTemplateType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateJasperReportTemplateCommand implements ICommand {

    private UUID id;
    private String code;
    private String name;
    private String description;
    private JasperReportTemplateType type;
    private byte[] file;
    private String parameters;

    public CreateJasperReportTemplateCommand(String code, String name, String description, JasperReportTemplateType type, byte[] file, String parameters) {
        this.id = UUID.randomUUID();
        this.code = code;
        this.name = name;
        this.description = description;
        this.type = type;
        this.file = file;
        this.parameters = parameters;
    }

    public static CreateJasperReportTemplateCommand fromRequest(CreateJasperReportTemplateRequest request) {
        return new CreateJasperReportTemplateCommand(
                request.getCode(), 
                request.getName(), 
                request.getDescription(), 
                request.getType(), 
                request.getFile(),
                request.getParameters()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateJasperReportTemplateMessage(id);
    }
}
