package com.kynsoft.report.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JasperReportTemplateDto {
    private UUID id;
    private String templateCode;
    private String templateName;
    private String templateDescription;
    private String templateContentUrl;
    private JasperReportTemplateType type;
    private String parameters;
}
