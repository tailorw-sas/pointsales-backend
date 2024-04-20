package com.kynsoft.notification.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.notification.application.command.jasperreporttemplate.create.CreateJasperReportTemplateMessage;
import com.kynsoft.notification.application.command.jasperreporttemplate.create.CreateJasperReportTemplateCommand;
import com.kynsoft.notification.application.command.jasperreporttemplate.create.CreateJasperReportTemplateRequest;
import com.kynsoft.notification.application.command.jasperreporttemplate.update.UpdateJasperReportTemplateCommand;
import com.kynsoft.notification.application.command.jasperreporttemplate.update.UpdateJasperReportTemplateMessage;
import com.kynsoft.notification.application.command.jasperreporttemplate.update.UpdateJasperReportTemplateRequest;
import com.kynsoft.notification.application.query.jasperreporttemplate.getbyid.FindJasperReportTemplateByIdQuery;
import com.kynsoft.notification.application.query.jasperreporttemplate.getbyid.JasperReportTemplateResponse;
import com.kynsoft.notification.application.query.jasperreporttemplate.search.GetJasperReportTemplateQuery;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<CreateJasperReportTemplateMessage> create(@RequestBody CreateJasperReportTemplateRequest request) {
        CreateJasperReportTemplateCommand createCommand = CreateJasperReportTemplateCommand.fromRequest(request);
        CreateJasperReportTemplateMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindJasperReportTemplateByIdQuery query = new FindJasperReportTemplateByIdQuery(id);
        JasperReportTemplateResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetJasperReportTemplateQuery query = new GetJasperReportTemplateQuery(pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateJasperReportTemplateMessage> update(@PathVariable("id") UUID id, @RequestBody UpdateJasperReportTemplateRequest request) {

        UpdateJasperReportTemplateCommand command = UpdateJasperReportTemplateCommand.fromRequest(request, id);
        UpdateJasperReportTemplateMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}