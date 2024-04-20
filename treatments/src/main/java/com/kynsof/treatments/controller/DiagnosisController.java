package com.kynsof.treatments.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.diagnosis.create.CreateDiagnosisCommand;
import com.kynsof.treatments.application.command.diagnosis.create.CreateDiagnosisMessage;
import com.kynsof.treatments.application.command.diagnosis.create.CreateDiagnosisRequest;
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

}
