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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final IMediator mediator;

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

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generatedReport(@RequestBody GenerateReportRequest request) {

        GenerateReportCommand command = new GenerateReportCommand(request.getParameters(),
                request.getJasperReportCode(), request.getReportFormatType());
        GenerateReportMessage response = mediator.send(command);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(response.getResult());
    }


    @GetMapping("/parameters/{reportCode}")
    public ResponseEntity<?> getReportParameters(@PathVariable String reportCode) {
        GetReportParameterByCodeQuery query = new GetReportParameterByCodeQuery(reportCode);
        GetReportParameterByCodeResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

}

