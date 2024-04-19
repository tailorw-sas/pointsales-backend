package com.kynsoft.notification.application.command.generateTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.jasperreports.engine.JREmptyDataSource;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateTemplateRequest {
    private Map<String, Object> parameters;
    private String JasperReportCode;
    private JREmptyDataSource jrEmptyDataSource;
}
