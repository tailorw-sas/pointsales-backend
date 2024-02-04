package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.command.service.create.CreateServiceCommand;
import com.kynsof.scheduled.application.command.service.create.CreateServiceMessage;
import com.kynsof.scheduled.application.command.service.create.CreateServiceRequest;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    private final IMediator mediator;

    public ServiceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateServiceMessage> create(@RequestBody CreateServiceRequest request)  {
        CreateServiceCommand createCommand = CreateServiceCommand.fromRequest(request);
        CreateServiceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

}
