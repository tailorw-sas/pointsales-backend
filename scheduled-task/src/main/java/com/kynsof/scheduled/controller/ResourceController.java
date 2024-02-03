package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.command.resource.create.CreateResourceCommand;
import com.kynsof.scheduled.application.command.resource.create.CreateResourceMessage;
import com.kynsof.scheduled.application.command.resource.create.CreateResourceRequest;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    private final IMediator mediator;

    public ResourceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateResourceMessage> create(@RequestBody CreateResourceRequest request)  {
        CreateResourceCommand createCommand = CreateResourceCommand.fromRequest(request);
        CreateResourceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

}
