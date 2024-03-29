package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.userrolbusiness.create.CreateUserRoleBusinessCommand;
import com.kynsof.identity.application.command.userrolbusiness.create.CreateUserRoleBusinessMessage;
import com.kynsof.identity.application.command.userrolbusiness.create.CreateUserRoleBusinessRequest;
import com.kynsof.identity.application.command.userrolbusiness.update.UpdateUserRoleBusinessCommand;
import com.kynsof.identity.application.command.userrolbusiness.update.UpdateUserRoleBusinessMessage;
import com.kynsof.identity.application.command.userrolbusiness.update.UpdateUserRoleBusinessRequest;
import com.kynsof.identity.application.query.rolpermission.getbyid.FindByIdUserRoleBusinessQuery;
import com.kynsof.identity.application.query.rolpermission.getbyid.UserRoleBusinessResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
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

}
