package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.userPermisionBusiness.create.CreateUserRoleBusinessCommand;
import com.kynsof.identity.application.command.userPermisionBusiness.create.CreateUserRoleBusinessMessage;
import com.kynsof.identity.application.command.userPermisionBusiness.create.CreateUserRoleBusinessRequest;
import com.kynsof.identity.application.command.userPermisionBusiness.create.lote.LoteCreateUserRoleBusinessCommand;
import com.kynsof.identity.application.command.userPermisionBusiness.create.lote.LoteCreateUserRoleBusinessMessage;
import com.kynsof.identity.application.command.userPermisionBusiness.create.lote.LoteCreateUserRoleBusinessRequest;
import com.kynsof.identity.application.command.userPermisionBusiness.delete.DeleteUserRolBusinessCommand;
import com.kynsof.identity.application.command.userPermisionBusiness.delete.DeleteUserRolBusinessMessage;
import com.kynsof.identity.application.command.userPermisionBusiness.update.UpdateUserRoleBusinessCommand;
import com.kynsof.identity.application.command.userPermisionBusiness.update.UpdateUserRoleBusinessMessage;
import com.kynsof.identity.application.command.userPermisionBusiness.update.UpdateUserRoleBusinessRequest;
import com.kynsof.identity.application.query.userrolbusiness.getbyid.FindByIdUserRoleBusinessQuery;
import com.kynsof.identity.application.query.userrolbusiness.getbyid.UserRoleBusinessResponse;
import com.kynsof.identity.application.query.userrolbusiness.search.GetSearchUserRolBusinessQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user-permission-business")
public class UserPermissionBusinessController {

    private final IMediator mediator;

    public UserPermissionBusinessController(IMediator mediator) {
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