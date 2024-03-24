package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.rolpermission.create.CreateRolPermissionCommand;
import com.kynsof.identity.application.command.rolpermission.create.CreateRolPermissionMessage;
import com.kynsof.identity.application.command.rolpermission.create.CreateRolPermissionRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role/permission")
public class RolePermissionController {

    private final IMediator mediator;

    public RolePermissionController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<CreateRolPermissionMessage> createRole(@RequestBody CreateRolPermissionRequest request) {
        CreateRolPermissionCommand command = CreateRolPermissionCommand.fromRequest(request);
        CreateRolPermissionMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }
}
