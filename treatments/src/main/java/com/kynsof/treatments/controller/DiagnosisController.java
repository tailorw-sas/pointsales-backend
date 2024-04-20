package com.kynsof.treatments.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.diagnosis.create.CreateDiagnosisCommand;
import com.kynsof.treatments.application.command.diagnosis.create.CreateDiagnosisMessage;
import com.kynsof.treatments.application.command.diagnosis.create.CreateDiagnosisRequest;
import com.kynsof.treatments.application.query.diagnosis.search.GetSearchDiagnosisQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diagnosis")
public class DiagnosisController {

    private final IMediator mediator;

    public DiagnosisController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateDiagnosisRequest request) {
        CreateDiagnosisCommand createCommand = CreateDiagnosisCommand.fromRequest(request);
        CreateDiagnosisMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchDiagnosisQuery query = new GetSearchDiagnosisQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

}
