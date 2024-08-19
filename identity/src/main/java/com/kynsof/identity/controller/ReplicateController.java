package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.replicate.objects.CreateReplicateCommand;
import com.kynsof.identity.application.command.replicate.objects.CreateReplicateMessage;
import com.kynsof.identity.application.command.replicate.objects.CreateReplicateRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/replicate")
public class ReplicateController {

    private final IMediator mediator;

    public ReplicateController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<CreateReplicateMessage> create(@RequestBody CreateReplicateRequest request) {
        CreateReplicateCommand createCommand = CreateReplicateCommand.fromRequest(request);
        CreateReplicateMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }
}
