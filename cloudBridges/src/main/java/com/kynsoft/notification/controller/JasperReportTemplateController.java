package com.kynsoft.notification.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.notification.application.command.jasperreporttemplate.create.CreateCreateJasperReportTemplateMessage;
import com.kynsoft.notification.application.command.jasperreporttemplate.create.CreateJasperReportTemplateCommand;
import com.kynsoft.notification.application.command.jasperreporttemplate.create.CreateJasperReportTemplateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jasper-report-template")
public class JasperReportTemplateController {

    private final IMediator mediator;

    @Autowired
    public JasperReportTemplateController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateCreateJasperReportTemplateMessage> create(@RequestBody CreateJasperReportTemplateRequest request)  {
        CreateJasperReportTemplateCommand createCommand = CreateJasperReportTemplateCommand.fromRequest(request);
        CreateCreateJasperReportTemplateMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }
}
