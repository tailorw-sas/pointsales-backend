package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.command.qualification.create.CreateQualificationCommand;
import com.kynsof.scheduled.application.command.qualification.create.CreateQualificationMessage;
import com.kynsof.scheduled.application.command.qualification.create.CreateQualificationRequest;
import com.kynsof.scheduled.application.command.qualification.delete.QualificationDeleteCommand;
import com.kynsof.scheduled.application.command.qualification.delete.QualificationDeleteMessage;
import com.kynsof.scheduled.application.command.qualification.update.UpdateQualificationCommand;
import com.kynsof.scheduled.application.command.qualification.update.UpdateQualificationMessage;
import com.kynsof.scheduled.application.command.qualification.update.UpdateQualificationRequest;
import com.kynsof.scheduled.application.query.QualificationResponse;
import com.kynsof.scheduled.application.query.qualification.getAll.FindQualificationWithFilterQuery;
import com.kynsof.scheduled.application.query.qualification.getbyid.FindQualificationByIdQuery;
import com.kynsof.share.core.infrastructure.bus.IMediator;

import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID idQualification,
                                                    @RequestParam(defaultValue = "") String filter)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        FindQualificationWithFilterQuery query = new FindQualificationWithFilterQuery(pageable, idQualification, filter);
        PaginatedResponse data = mediator.send(query);

        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<QualificationResponse> getById(@PathVariable UUID id) {

        FindQualificationByIdQuery query = new FindQualificationByIdQuery(id);
        QualificationResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QualificationDeleteMessage> deleteServices(@PathVariable("id") UUID id) {

        QualificationDeleteCommand command = new QualificationDeleteCommand(id);
        QualificationDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<UpdateQualificationMessage> update(@RequestBody UpdateQualificationRequest request) {

        UpdateQualificationCommand command = UpdateQualificationCommand.fromRequest(request);
        UpdateQualificationMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
