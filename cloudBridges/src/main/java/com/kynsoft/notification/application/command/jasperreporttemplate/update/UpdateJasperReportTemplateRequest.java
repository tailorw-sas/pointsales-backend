package com.kynsoft.notification.application.command.jasperreporttemplate.update;

import com.kynsoft.notification.domain.dto.JasperReportTemplateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJasperReportTemplateRequest {
    private String code;
    private String name;
    private String description;
    private JasperReportTemplateType type;
    private byte[] file;
}
