package com.kynsoft.report.domain.dto;

import com.kynsoft.report.domain.dto.status.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JasperReportTemplateDto {
    private  UUID id;
    private  String templateCode;
    private  String templateName;
    private  String templateDescription;
    private  String templateContentUrl;
    private  JasperReportTemplateType type;
    private  String parameters;
    private LocalDateTime createdAt;
    private DBConectionDto dbConection;
    private String query;
    private Status status;

    public JasperReportTemplateDto(UUID id, String templateCode, String templateName, String templateDescription,
                                   String templateContentUrl, JasperReportTemplateType type, String parameters) {
        this.id = id;
        this.templateCode = templateCode;
        this.templateName = templateName;
        this.templateDescription = templateDescription;
        this.templateContentUrl = templateContentUrl;
        this.type = type;
        this.parameters = parameters;
    }

    public JasperReportTemplateDto(UUID id, String templateCode, String templateName, String templateDescription,
                                   String templateContentUrl, JasperReportTemplateType type, String parameters,
                                   DBConectionDto dbConection, String query, Status status) {
        this.id = id;
        this.templateCode = templateCode;
        this.templateName = templateName;
        this.templateDescription = templateDescription;
        this.templateContentUrl = templateContentUrl;
        this.type = type;
        this.parameters = parameters;
        this.dbConection = dbConection;
        this.query = query;
        this.status = status;
    }
}
