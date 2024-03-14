package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.user.create.CreateUserSystemCommand;
import com.kynsof.identity.application.command.user.create.CreateUserSystemMessage;
import com.kynsof.identity.application.command.user.create.CreateUserSystemRequest;
import com.kynsof.identity.application.command.user.delete.DeleteUserSystemsCommand;
import com.kynsof.identity.application.command.user.delete.DeleteUserSystemsMessage;
import com.kynsof.identity.application.command.user.update.UpdateUserSystemCommand;
import com.kynsof.identity.application.command.user.update.UpdateUserSystemMessage;
import com.kynsof.identity.application.command.user.update.UpdateUserSystemRequest;
import com.kynsof.identity.application.query.users.getById.FindByIdUserSystemsQuery;
import com.kynsof.identity.application.query.users.getById.UserSystemsByIdResponse;
import com.kynsof.identity.application.query.users.getall.GetSearchUserSystemsQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindByIdUserSystemsQuery query = new FindByIdUserSystemsQuery(id);
        UserSystemsByIdResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UpdateUserSystemRequest request) {

        UpdateUserSystemCommand command = UpdateUserSystemCommand.fromRequest(id, request);
        UpdateUserSystemMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServices(@PathVariable("id") UUID id) {

        DeleteUserSystemsCommand command = new DeleteUserSystemsCommand(id);
        DeleteUserSystemsMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchUserSystemsQuery query = new GetSearchUserSystemsQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }
}
