package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.rolpermission.create.CreateRolPermissionCommand;
import com.kynsof.identity.application.command.rolpermission.create.CreateRolPermissionMessage;
import com.kynsof.identity.application.command.rolpermission.create.CreateRolPermissionRequest;
import com.kynsof.identity.application.query.rolpermission.getById.FindRolPermissionByIdQuery;
import com.kynsof.identity.application.query.rolpermission.getById.RolPermissionResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<RolPermissionResponse> getById(@PathVariable UUID id) {

        FindRolPermissionByIdQuery query = new FindRolPermissionByIdQuery(id);
        RolPermissionResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

}
