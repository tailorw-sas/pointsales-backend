package com.kynsoft.notification.infrastructure.entity;

import com.kynsof.share.core.domain.BaseEntity;
import com.kynsoft.notification.domain.dto.JasperReportTemplateDto;
import com.kynsoft.notification.domain.dto.JasperReportTemplateType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jasper_report_template")
public class JasperReportTemplate extends BaseEntity {
    private String templateCode;
    private String templateName;
    private String templateDescription;
    private String templateContentUrl;
    @Enumerated(EnumType.STRING)
    private JasperReportTemplateType type;

    private String parameters;

    public JasperReportTemplate(JasperReportTemplateDto jasperReportTemplateDto) {
        this.id = jasperReportTemplateDto.getId();
        this.templateCode = jasperReportTemplateDto.getTemplateCode();
        this.templateName = jasperReportTemplateDto.getTemplateName();
        this.templateDescription = jasperReportTemplateDto.getTemplateDescription();
        this.templateContentUrl = jasperReportTemplateDto.getTemplateContentUrl();
        this.type = jasperReportTemplateDto.getType();
        this.parameters = jasperReportTemplateDto.getParameters();
    }

    public JasperReportTemplateDto toAggregate () {
        String templateContentUrlS = templateContentUrl != null ? templateContentUrl : null;

        return new JasperReportTemplateDto(id, templateCode, templateName, templateDescription, templateContentUrlS, type, parameters);
    }

}