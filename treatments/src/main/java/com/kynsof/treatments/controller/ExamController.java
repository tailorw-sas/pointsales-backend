package com.kynsof.treatments.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.exam.create.CreateExamCommand;
import com.kynsof.treatments.application.command.exam.create.CreateExamMessage;
import com.kynsof.treatments.application.command.exam.create.CreateExamRequest;
import com.kynsof.treatments.application.query.exam.getbyid.ExamResponse;
import com.kynsof.treatments.application.query.exam.getbyid.FindByIdExamQuery;
import java.util.UUID;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExamResponse> getById(@PathVariable UUID id) {

        FindByIdExamQuery query = new FindByIdExamQuery(id);
        ExamResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

}
