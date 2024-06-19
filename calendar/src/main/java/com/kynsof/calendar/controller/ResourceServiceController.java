package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.resource.addServices.AddServiceCommand;
import com.kynsof.calendar.application.command.resource.addServices.AddServiceMessage;
import com.kynsof.calendar.application.command.resource.addServices.Create;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource-services")
public class ResourceServiceController {

    private final IMediator mediator;

    public ResourceServiceController(IMediator mediator){

        this.mediator = mediator;
    }


    @PostMapping("")
    public ResponseEntity<?> addServices( @RequestBody Create request)
    {
        AddServiceCommand command = AddServiceCommand.fromRequest(request);
        AddServiceMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
