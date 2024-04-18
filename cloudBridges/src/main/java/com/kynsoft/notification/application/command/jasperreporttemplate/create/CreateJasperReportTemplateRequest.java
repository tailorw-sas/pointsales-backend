package com.kynsoft.notification.application.command.jasperreporttemplate.create;

import com.kynsoft.notification.domain.dto.JasperReportTemplateType;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateJasperReportTemplateRequest {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private JasperReportTemplateType type;
    private byte[] file;
}
