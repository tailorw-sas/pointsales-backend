package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.businessresource.create.CreateBusinessResourceCommand;
import com.kynsof.calendar.application.command.businessresource.create.CreateBusinessResourceMessage;
import com.kynsof.calendar.application.command.businessresource.create.CreateBusinessresourceRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/business-resource")
public class BusinessResourceController {

    private final IMediator mediator;

    public BusinessResourceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateBusinessresourceRequest request)  {
        CreateBusinessResourceCommand createCommand = CreateBusinessResourceCommand.fromRequest(request);
        CreateBusinessResourceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }
}
