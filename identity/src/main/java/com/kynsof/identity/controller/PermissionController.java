package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.permission.create.CreatePermissionCommand;
import com.kynsof.identity.application.command.permission.create.CreatePermissionMessage;
import com.kynsof.identity.application.command.permission.create.CreatePermissionRequest;
import com.kynsof.identity.application.query.permission.getById.FindPermissionByIdQuery;
import com.kynsof.identity.application.query.permission.getById.PermissionrResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    private final IMediator mediator;

    public PermissionController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreatePermissionMessage> create(@RequestBody CreatePermissionRequest request) {
        CreatePermissionCommand createCommand = CreatePermissionCommand.fromRequest(request);
        CreatePermissionMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PermissionrResponse> getById(@PathVariable UUID id) {

        FindPermissionByIdQuery query = new FindPermissionByIdQuery(id);
        PermissionrResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

}
