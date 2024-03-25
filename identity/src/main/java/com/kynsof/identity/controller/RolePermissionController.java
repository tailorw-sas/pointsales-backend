package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.rolpermission.create.CreateRolPermissionCommand;
import com.kynsof.identity.application.command.rolpermission.create.CreateRolPermissionMessage;
import com.kynsof.identity.application.command.rolpermission.create.CreateRolPermissionRequest;
import com.kynsof.identity.application.command.rolpermission.delete.DeleteRolPermissionCommand;
import com.kynsof.identity.application.command.rolpermission.delete.DeleteRolPermissionMessage;
import com.kynsof.identity.application.command.rolpermission.update.UpdateRolPermissionCommand;
import com.kynsof.identity.application.command.rolpermission.update.UpdateRolPermissionMessage;
import com.kynsof.identity.application.command.rolpermission.update.UpdateRolPermissionRequest;
import com.kynsof.identity.application.query.rolpermission.getById.FindRolPermissionByIdQuery;
import com.kynsof.identity.application.query.rolpermission.getById.RolPermissionResponse;
import com.kynsof.identity.application.query.rolpermission.search.GetSearchRolPermissionQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @PutMapping()
    public ResponseEntity<UpdateRolPermissionMessage> update(@RequestBody UpdateRolPermissionRequest request) {

        UpdateRolPermissionCommand command = UpdateRolPermissionCommand.fromRequest(request);
        UpdateRolPermissionMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RolPermissionResponse> getById(@PathVariable UUID id) {

        FindRolPermissionByIdQuery query = new FindRolPermissionByIdQuery(id);
        RolPermissionResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteRolPermissionMessage> delete(@PathVariable("id") UUID id) {

        DeleteRolPermissionCommand command = new DeleteRolPermissionCommand(id);
        DeleteRolPermissionMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchRolPermissionQuery query = new GetSearchRolPermissionQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

}
