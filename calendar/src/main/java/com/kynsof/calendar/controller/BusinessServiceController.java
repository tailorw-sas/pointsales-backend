package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.businessservices.create.CreateBusinessServicesCommand;
import com.kynsof.calendar.application.command.businessservices.create.CreateBusinessServicesMessage;
import com.kynsof.calendar.application.command.businessservices.create.CreateBusinessServicesRequest;
import com.kynsof.calendar.application.query.businesservice.getbyid.BusinessServicesResponse;
import com.kynsof.calendar.application.query.businesservice.getbyid.FindBusinessServiceByIdQuery;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/business-services")
public class BusinessServiceController {

    private final IMediator mediator;

    public BusinessServiceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateBusinessServicesRequest request)  {
        CreateBusinessServicesCommand createCommand = CreateBusinessServicesCommand.fromRequest(request);
        CreateBusinessServicesMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindBusinessServiceByIdQuery query = new FindBusinessServiceByIdQuery(id);
        BusinessServicesResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }
}
