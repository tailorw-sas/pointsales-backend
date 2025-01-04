package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.session.create.CreateSessionCommand;
import com.kynsof.identity.application.command.session.create.CreateSessionMessage;
import com.kynsof.identity.application.command.session.create.CreateSessionRequest;
import com.kynsof.identity.application.command.session.delete.DeleteSessionCommand;
import com.kynsof.identity.application.command.session.delete.DeleteSessionMessage;
import com.kynsof.identity.application.command.session.update.UpdateSessionCommand;
import com.kynsof.identity.application.command.session.update.UpdateSessionMessage;
import com.kynsof.identity.application.command.session.update.UpdateSessionRequest;
import com.kynsof.identity.application.query.session.getbyid.SessionByIdResponse;
import com.kynsof.identity.application.query.session.getbyid.FindSessionByIdQuery;
import com.kynsof.identity.application.query.session.search.GetSearchSessionQuery;
import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    private final IMediator mediator;

    public SessionController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<CreateSessionMessage> create(@RequestBody CreateSessionRequest request) {
        CreateSessionCommand createCommand = CreateSessionCommand.fromRequest(request);
        CreateSessionMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageableUtil.createPageable(request);

        GetSearchSessionQuery query = new GetSearchSessionQuery(
                pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        FindSessionByIdQuery query = new FindSessionByIdQuery(id);
        SessionByIdResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {

        DeleteSessionCommand command = new DeleteSessionCommand(id);
        DeleteSessionMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id,
                                    @RequestBody UpdateSessionRequest request) {

        UpdateSessionCommand command = UpdateSessionCommand.fromRequest(request, id);
        UpdateSessionMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
