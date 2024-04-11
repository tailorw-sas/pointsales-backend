package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.customer.create.CreateCustomerCommand;
import com.kynsof.identity.application.command.customer.create.CreateCustomerMessage;
import com.kynsof.identity.application.command.customer.create.CreateCustomerRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CutomerController {

    private final IMediator mediator;

    public CutomerController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<CreateCustomerMessage> create(@RequestBody CreateCustomerRequest request)  {
        CreateCustomerCommand createCommand = CreateCustomerCommand.fromRequest(request);
        CreateCustomerMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }
}
