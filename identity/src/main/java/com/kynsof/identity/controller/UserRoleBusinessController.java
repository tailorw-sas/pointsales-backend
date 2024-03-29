package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.userrolbusiness.create.CreateUserRoleBusinessCommand;
import com.kynsof.identity.application.command.userrolbusiness.create.CreateUserRoleBusinessMessage;
import com.kynsof.identity.application.command.userrolbusiness.create.CreateUserRoleBusinessRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/role/business")
public class UserRoleBusinessController {

    private final IMediator mediator;

    public UserRoleBusinessController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<CreateUserRoleBusinessMessage> createRole(@RequestBody CreateUserRoleBusinessRequest request) {
        CreateUserRoleBusinessCommand command = CreateUserRoleBusinessCommand.fromRequest(request);
        CreateUserRoleBusinessMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
