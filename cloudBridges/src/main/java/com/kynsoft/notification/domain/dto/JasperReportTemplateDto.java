package com.kynsoft.notification.domain.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
