package com.kynsoft.report.controller;

import com.kynsoft.report.applications.command.generateTemplate.GenerateTemplateCommand;
import com.kynsoft.report.applications.command.generateTemplate.GenerateTemplateMessage;
import com.kynsoft.report.applications.command.generateTemplate.GenerateTemplateRequest;
import com.kynsoft.report.applications.query.reportTemplate.GetReportParameterByCodeQuery;
import com.kynsoft.report.applications.query.reportTemplate.GetReportParameterByCodeResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final IMediator medietor;

    public ReportController(IMediator medietor) {
        this.medietor = medietor;
    }

    @PostMapping(value = "/generate-template", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> getTemplate(@RequestBody GenerateTemplateRequest request) {

        GenerateTemplateCommand command = new GenerateTemplateCommand(request.getParameters(),
                request.getJasperReportCode());
        GenerateTemplateMessage response = medietor.send(command);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(response.getResult());
    }


    @GetMapping("/parameters/{reportCode}")
    public ResponseEntity<?> getReportParameters(@PathVariable String reportCode) {
        GetReportParameterByCodeQuery query = new GetReportParameterByCodeQuery(reportCode);
        GetReportParameterByCodeResponse response = medietor.send(query);
        return ResponseEntity.ok(response);
    }

}

