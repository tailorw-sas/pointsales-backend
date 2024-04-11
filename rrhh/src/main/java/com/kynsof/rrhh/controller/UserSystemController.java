package com.kynsof.rrhh.controller;

import com.kynsof.rrhh.application.command.registerFingerprint.RegisterFingerprintRequest;
import com.kynsof.rrhh.application.command.users.create.CreateUserCommand;
import com.kynsof.rrhh.application.command.users.create.CreateUserMessage;
import com.kynsof.rrhh.application.command.users.create.CreateUserRequest;
import com.kynsof.rrhh.application.command.users.update.UpdateUserCommand;
import com.kynsof.rrhh.application.command.users.update.UpdateUserMessage;
import com.kynsof.rrhh.application.command.users.update.UpdateUserRequest;
import com.kynsof.rrhh.application.query.users.getbyid.FindByIdUserSystemsQuery;
import com.kynsof.rrhh.application.query.users.getbyid.UserSystemsByIdResponse;
import com.kynsof.rrhh.application.query.users.search.GetSearchUsersQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserSystemController {

    private final IMediator mediator;

    public UserSystemController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<CreateUserMessage> create(@RequestBody CreateUserRequest request)  {
        CreateUserCommand createCommand = CreateUserCommand.fromRequest(request);
        CreateUserMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchUsersQuery query = new GetSearchUsersQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateUserMessage> update(@PathVariable("id") UUID id, @RequestBody UpdateUserRequest request) {

        UpdateUserCommand command = UpdateUserCommand.fromRequest(request,id);
        UpdateUserMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("register-fingerprint")
    public Mono<ResponseEntity<?>> registerFingerprint(@RequestBody RegisterFingerprintRequest request){
        return Mono.just(ResponseEntity.ok("Keycloak with ADMIN CLIENT ROLE"));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserSystemsByIdResponse> getById(@PathVariable UUID id) {

        FindByIdUserSystemsQuery query = new FindByIdUserSystemsQuery(id);
        UserSystemsByIdResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    //TODO
    // Dado una cedula devolver el usuario
    // Dado el id de un equipo devolver los usuarios que pertenecen a la empresa del equipo pasado
    // Crear Crud para administrar equipos Servicio(ip,SerieId,businessId)
}
