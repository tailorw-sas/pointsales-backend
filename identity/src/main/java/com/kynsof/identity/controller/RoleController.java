package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.role.create.CreateRoleCommand;
import com.kynsof.identity.application.command.role.create.CreateRoleMessage;
import com.kynsof.identity.application.command.role.create.RoleRequest;
import com.kynsof.identity.application.command.role.delete.DeleteRoleSystemsCommand;
import com.kynsof.identity.application.command.role.delete.DeleteRoleSystemsMessage;
import com.kynsof.identity.application.command.role.update.UpdateRoleCommand;
import com.kynsof.identity.application.command.role.update.UpdateRoleMessage;
import com.kynsof.identity.application.query.roles.getById.FindByIdRoleSystemsQuery;
import com.kynsof.identity.application.query.roles.getSearch.GetSearchRoleSystemsQuery;
import com.kynsof.identity.application.query.roles.getSearch.RoleSystemsResponse;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServices(@PathVariable("id") UUID id) {

        DeleteRoleSystemsCommand command = new DeleteRoleSystemsCommand(id);
        DeleteRoleSystemsMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchRoleSystemsQuery query = new GetSearchRoleSystemsQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindByIdRoleSystemsQuery query = new FindByIdRoleSystemsQuery(id);
        RoleSystemsResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }
}
