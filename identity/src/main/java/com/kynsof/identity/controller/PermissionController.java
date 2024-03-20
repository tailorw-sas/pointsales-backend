package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.permission.create.CreatePermissionCommand;
import com.kynsof.identity.application.command.permission.create.CreatePermissionMessage;
import com.kynsof.identity.application.command.permission.create.CreatePermissionRequest;
import com.kynsof.identity.application.command.permission.delete.DeletePermissionCommand;
import com.kynsof.identity.application.command.permission.delete.DeletePermissionMessage;
import com.kynsof.identity.application.command.permission.update.UpdatePermissionCommand;
import com.kynsof.identity.application.command.permission.update.UpdatePermissionMessage;
import com.kynsof.identity.application.command.permission.update.UpdatePermissionRequest;
import com.kynsof.identity.application.query.permission.getById.FindPermissionByIdQuery;
import com.kynsof.identity.application.query.permission.getById.PermissionrResponse;
import com.kynsof.identity.application.query.permission.search.GetSearchPermissionQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @PutMapping()
    public ResponseEntity<UpdatePermissionMessage> update(@RequestBody UpdatePermissionRequest request) {

        UpdatePermissionCommand command = UpdatePermissionCommand.fromRequest(request);
        UpdatePermissionMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PermissionrResponse> getById(@PathVariable UUID id) {

        FindPermissionByIdQuery query = new FindPermissionByIdQuery(id);
        PermissionrResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchPermissionQuery query = new GetSearchPermissionQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletePermissionMessage> delete(@PathVariable("id") UUID id) {

        DeletePermissionCommand command = new DeletePermissionCommand(id);
        DeletePermissionMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

}
