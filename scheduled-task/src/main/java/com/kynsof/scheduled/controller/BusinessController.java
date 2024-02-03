package com.kynsof.scheduled.controller;


import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.command.business.create.CreateBusinessCommand;
import com.kynsof.scheduled.application.command.business.create.CreateBusinessMessage;
import com.kynsof.scheduled.application.command.business.create.CreateBusinessRequest;
import com.kynsof.scheduled.application.command.business.delete.BusinessDeleteCommand;
import com.kynsof.scheduled.application.command.business.delete.BusinessDeleteMessage;
import com.kynsof.scheduled.application.command.qualification.create.CreateQualificationCommand;
import com.kynsof.scheduled.application.command.qualification.create.CreateQualificationMessage;
import com.kynsof.scheduled.application.command.qualification.create.CreateQualificationRequest;
import com.kynsof.scheduled.application.command.qualification.delete.QualificationDeleteCommand;
import com.kynsof.scheduled.application.command.qualification.delete.QualificationDeleteMessage;
import com.kynsof.scheduled.application.command.qualification.update.UpdateQualificationCommand;
import com.kynsof.scheduled.application.command.qualification.update.UpdateQualificationMessage;
import com.kynsof.scheduled.application.command.qualification.update.UpdateQualificationRequest;
import com.kynsof.scheduled.application.query.BusinessResponse;
import com.kynsof.scheduled.application.query.QualificationResponse;
import com.kynsof.scheduled.application.query.business.getAll.FindBusinessWithFilterQuery;
import com.kynsof.scheduled.application.query.business.getbyid.FindBusinessByIdQuery;
import com.kynsof.scheduled.application.query.qualification.getAll.FindQualificationWithFilterQuery;
import com.kynsof.scheduled.application.query.qualification.getbyid.FindQualificationByIdQuery;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/business")
public class BusinessController {

    private final IMediator mediator;

    public BusinessController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateBusinessMessage> create(@RequestBody CreateBusinessRequest request)  {
        CreateBusinessCommand createCommand = CreateBusinessCommand.fromRequest(request);
        CreateBusinessMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID idBusiness,
                                                    @RequestParam(defaultValue = "") String filter)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        FindBusinessWithFilterQuery query = new FindBusinessWithFilterQuery(pageable, idBusiness, filter);
        PaginatedResponse data = mediator.send(query);

        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BusinessResponse> getById(@PathVariable UUID id) {

        FindBusinessByIdQuery query = new FindBusinessByIdQuery(id);
        BusinessResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BusinessDeleteMessage> delete(@PathVariable("id") UUID id) {

        BusinessDeleteCommand command = new BusinessDeleteCommand(id);
        BusinessDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

}
