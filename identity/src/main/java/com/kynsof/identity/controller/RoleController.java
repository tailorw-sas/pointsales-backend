package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.role.create.CreatRoleMessage;
import com.kynsof.identity.application.command.role.create.CreateRoleCommand;
import com.kynsof.identity.application.command.role.create.CreateRoleRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final IMediator mediator;

    public RoleController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<CreatRoleMessage> createRole(@RequestBody CreateRoleRequest request) {
        CreateRoleCommand command = CreateRoleCommand.fromRequest(request);
        CreatRoleMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }
}
