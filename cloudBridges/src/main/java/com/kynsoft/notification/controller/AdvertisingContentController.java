package com.kynsoft.notification.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.notification.application.command.advertisingcontent.create.CreateAdvertisingContentCommand;
import com.kynsoft.notification.application.command.advertisingcontent.create.CreateAdvertisingContentMessage;
import com.kynsoft.notification.application.command.advertisingcontent.create.CreateAdvertisingContentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advertising-content")
public class AdvertisingContentController {

    private final IMediator mediator;

    @Autowired
    public AdvertisingContentController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateAdvertisingContentMessage> create(@RequestBody CreateAdvertisingContentRequest request)  {
        CreateAdvertisingContentCommand createCommand = CreateAdvertisingContentCommand.fromRequest(request);
        CreateAdvertisingContentMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

}
