package com.kynsoft.notification.application.query.jasperreporttemplate.getbyid;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsoft.notification.domain.dto.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JasperReportTemplateResponse implements IResponse {
    private UUID id;
    private String templateCode;
    private String templateName;
    private String templateDescription;
    private String templateContentUrl;
    private JasperReportTemplateType type;

    public JasperReportTemplateResponse(JasperReportTemplateDto jasperReportTemplateDto) {
        this.id = jasperReportTemplateDto.getId();
        this.templateCode = jasperReportTemplateDto.getTemplateCode();
        this.templateName = jasperReportTemplateDto.getTemplateName();
        this.templateDescription = jasperReportTemplateDto.getTemplateDescription();
        this.templateContentUrl = jasperReportTemplateDto.getTemplateContentUrl();
        this.type = jasperReportTemplateDto.getType();
    }

}
