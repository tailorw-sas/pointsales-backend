package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.firebaseToken.create.CreateFirebaseTokenCommand;
import com.kynsof.identity.application.command.firebaseToken.create.CreateFirebaseTokenMessage;
import com.kynsof.identity.application.command.firebaseToken.create.CreateFirebaseTokenRequest;
import com.kynsof.identity.application.command.firebaseToken.update.UpdateFirebaseTokenCommand;
import com.kynsof.identity.application.command.firebaseToken.update.UpdateFirebaseTokenMessage;
import com.kynsof.identity.application.query.firebaseToken.getbyid.FindFirebaseTokenByIdQuery;
import com.kynsof.identity.application.query.firebaseToken.getbyid.FirebaseTokenResponse;
import com.kynsof.identity.application.query.firebaseToken.search.GetSearchFirebaseTokenQuery;
import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/firebase-token")
public class FirebaseTokenController {

    private final IMediator mediator;

    public FirebaseTokenController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody com.kynsof.identity.application.command.firebaseToken.create.CreateFirebaseTokenRequest request) {
        CreateFirebaseTokenCommand createCommand = CreateFirebaseTokenCommand.fromRequest(request);
        CreateFirebaseTokenMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageableUtil.createPageable(request);

        GetSearchFirebaseTokenQuery query = new GetSearchFirebaseTokenQuery(pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindFirebaseTokenByIdQuery query = new FindFirebaseTokenByIdQuery(id);
        FirebaseTokenResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
//
//        FirebaseTokenDeleteCommand command = new FirebaseTokenDeleteCommand(id);
//        FirebaseTokenDeleteMessage response = mediator.send(command);
//
//        return ResponseEntity.ok(response);
//    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody CreateFirebaseTokenRequest request) {

        UpdateFirebaseTokenCommand command = UpdateFirebaseTokenCommand.fromRequest(request, id);
        UpdateFirebaseTokenMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }
}
