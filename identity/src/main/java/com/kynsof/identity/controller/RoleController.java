package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.role.create.CreateRoleCommand;
import com.kynsof.identity.application.command.role.create.CreateRoleMessage;
import com.kynsof.identity.application.command.role.create.RoleRequest;
import com.kynsof.identity.application.command.role.update.UpdateRoleCommand;
import com.kynsof.identity.application.command.role.update.UpdateRoleMessage;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final IMediator mediator;

    public RoleController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<CreateRoleMessage> createRole(@RequestBody RoleRequest request) {
        CreateRoleCommand command = CreateRoleCommand.fromRequest(request);
        CreateRoleMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateRoleMessage> updateRole(@PathVariable UUID id, @RequestBody RoleRequest roleUpdateDto) {
        UpdateRoleCommand command = UpdateRoleCommand.fromRequest(id,roleUpdateDto);
        UpdateRoleMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }
}
