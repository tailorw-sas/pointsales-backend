package com.kynsof.treatments.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.exam.create.CreateExamCommand;
import com.kynsof.treatments.application.command.exam.create.CreateExamMessage;
import com.kynsof.treatments.application.command.exam.create.CreateExamRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam")
public class ExamController {

    private final IMediator mediator;

    public ExamController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateExamRequest request) {
        CreateExamCommand createCommand = CreateExamCommand.fromRequest(request);
        CreateExamMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }
}
