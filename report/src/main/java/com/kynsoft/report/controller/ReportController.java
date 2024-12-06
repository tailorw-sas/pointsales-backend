package com.kynsoft.report.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.report.applications.command.generateReport.GenerateReportCommand;
import com.kynsoft.report.applications.command.generateReport.GenerateReportMessage;
import com.kynsoft.report.applications.command.generateReport.GenerateReportRequest;
import com.kynsoft.report.applications.command.generateTemplate1.GenerateTemplateCommand;
import com.kynsoft.report.applications.command.generateTemplate1.GenerateTemplateMessage;
import com.kynsoft.report.applications.command.generateTemplate1.GenerateTemplateRequest;
import com.kynsoft.report.applications.query.reportTemplate.GetReportParameterByCodeQuery;
import com.kynsoft.report.applications.query.reportTemplate.GetReportParameterByCodeResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final IMediator mediator;
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    public ReportController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping(value = "/generate-template", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> getTemplate(@RequestBody GenerateTemplateRequest request) {

        GenerateTemplateCommand command = new GenerateTemplateCommand(request.getParameters(),
                request.getJasperReportCode());
        GenerateTemplateMessage response = mediator.send(command);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(response.getResult());
    }

    @PostMapping(value = "/generate-report", produces = {MediaType.APPLICATION_PDF_VALUE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    public ResponseEntity<?> generatedReport(@Valid @RequestBody GenerateReportRequest request) {

        logger.info("Request received to generate report: {}", request);

        GenerateReportCommand command = new GenerateReportCommand(request.getParameters(),
                request.getJasperReportCode(), request.getReportFormatType());

        try {
            GenerateReportMessage response = mediator.send(command);

            MediaType contentType = "XLS".equalsIgnoreCase(request.getReportFormatType())
                    ? MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    : MediaType.APPLICATION_PDF;

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report." + request.getReportFormatType().toLowerCase())
                    .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
                    .contentType(contentType)
                    .body(response.getResult());
        } catch (Exception e) {
            logger.error("Error generating report: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating report: " + e.getMessage());
        }
    }


    @GetMapping("/parameters/{reportCode}")
    public ResponseEntity<?> getReportParameters(@PathVariable String reportCode) {
        GetReportParameterByCodeQuery query = new GetReportParameterByCodeQuery(reportCode);
        GetReportParameterByCodeResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

}

