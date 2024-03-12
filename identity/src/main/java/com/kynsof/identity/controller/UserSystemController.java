package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.user.create.CreateUserSystemCommand;
import com.kynsof.identity.application.command.user.create.CreateUserSystemMessage;
import com.kynsof.identity.application.command.user.create.CreateUserSystemRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserSystemController {

    private final IMediator mediator;

    @Autowired
    public UserSystemController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<CreateUserSystemMessage> createUserSystem(@RequestBody CreateUserSystemRequest request) {
        CreateUserSystemCommand command = CreateUserSystemCommand.fromRequest(request);
        CreateUserSystemMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }
}
