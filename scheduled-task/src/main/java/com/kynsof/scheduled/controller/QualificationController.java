package com.kynsof.scheduled.controller;


import com.kynsof.scheduled.application.command.qualification.create.CreateQualificationCommand;
import com.kynsof.scheduled.application.command.qualification.create.CreateQualificationMessage;
import com.kynsof.scheduled.application.command.qualification.create.CreateQualificationRequest;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qualification")
public class QualificationController {

    private final IMediator mediator;

    public QualificationController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateQualificationMessage> create(@RequestBody CreateQualificationRequest request)  {
        CreateQualificationCommand createCommand = CreateQualificationCommand.fromRequest(request);
        CreateQualificationMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }
}
