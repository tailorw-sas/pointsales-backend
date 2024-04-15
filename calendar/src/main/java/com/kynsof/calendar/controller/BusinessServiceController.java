package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.businessservices.create.CreateBusinessServicesCommand;
import com.kynsof.calendar.application.command.businessservices.create.CreateBusinessServicesMessage;
import com.kynsof.calendar.application.command.businessservices.create.CreateBusinessServicesRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
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

}
