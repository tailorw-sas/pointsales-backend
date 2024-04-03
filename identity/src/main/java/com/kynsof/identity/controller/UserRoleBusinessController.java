package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.userrolbusiness.create.CreateUserRoleBusinessCommand;
import com.kynsof.identity.application.command.userrolbusiness.create.CreateUserRoleBusinessMessage;
import com.kynsof.identity.application.command.userrolbusiness.create.CreateUserRoleBusinessRequest;
import com.kynsof.identity.application.command.userrolbusiness.create.lote.LoteCreateUserRoleBusinessCommand;
import com.kynsof.identity.application.command.userrolbusiness.create.lote.LoteCreateUserRoleBusinessMessage;
import com.kynsof.identity.application.command.userrolbusiness.create.lote.LoteCreateUserRoleBusinessRequest;
import com.kynsof.identity.application.command.userrolbusiness.delete.DeleteUserRolBusinessCommand;
import com.kynsof.identity.application.command.userrolbusiness.delete.DeleteUserRolBusinessMessage;
import com.kynsof.identity.application.command.userrolbusiness.update.UpdateUserRoleBusinessCommand;
import com.kynsof.identity.application.command.userrolbusiness.update.UpdateUserRoleBusinessMessage;
import com.kynsof.identity.application.command.userrolbusiness.update.UpdateUserRoleBusinessRequest;
import com.kynsof.identity.application.query.userrolbusiness.getbyid.FindByIdUserRoleBusinessQuery;
import com.kynsof.identity.application.query.userrolbusiness.getbyid.UserRoleBusinessResponse;
import com.kynsof.identity.application.query.userrolbusiness.search.GetSearchUserRolBusinessQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<CreateUserRoleBusinessMessage> create(@RequestBody CreateUserRoleBusinessRequest request) {
        CreateUserRoleBusinessCommand command = CreateUserRoleBusinessCommand.fromRequest(request);
        CreateUserRoleBusinessMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create/lote")
    public ResponseEntity<LoteCreateUserRoleBusinessMessage> createLote(@RequestBody LoteCreateUserRoleBusinessRequest request) {
        LoteCreateUserRoleBusinessCommand command = LoteCreateUserRoleBusinessCommand.fromRequest(request);
        LoteCreateUserRoleBusinessMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<UpdateUserRoleBusinessMessage> update(@RequestBody UpdateUserRoleBusinessRequest request) {
        UpdateUserRoleBusinessCommand command = UpdateUserRoleBusinessCommand.fromRequest(request);
        UpdateUserRoleBusinessMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserRoleBusinessResponse> getById(@PathVariable UUID id) {

        FindByIdUserRoleBusinessQuery query = new FindByIdUserRoleBusinessQuery(id);
        UserRoleBusinessResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteUserRolBusinessMessage> delete(@PathVariable("id") UUID id) {

        DeleteUserRolBusinessCommand command = new DeleteUserRolBusinessCommand(id);
        DeleteUserRolBusinessMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchUserRolBusinessQuery query = new GetSearchUserRolBusinessQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

}
