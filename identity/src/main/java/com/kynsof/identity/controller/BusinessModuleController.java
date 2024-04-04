package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.businessmodule.create.CreateBusinessModuleCommand;
import com.kynsof.identity.application.command.businessmodule.create.CreateBusinessModuleMessage;
import com.kynsof.identity.application.command.businessmodule.create.CreateBusinessModuleRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/business-module")
public class BusinessModuleController {

    private final IMediator mediator;

    public BusinessModuleController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<CreateBusinessModuleMessage> create(@RequestBody CreateBusinessModuleRequest request)  {
        CreateBusinessModuleCommand createCommand = CreateBusinessModuleCommand.fromRequest(request);
        CreateBusinessModuleMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }
}
