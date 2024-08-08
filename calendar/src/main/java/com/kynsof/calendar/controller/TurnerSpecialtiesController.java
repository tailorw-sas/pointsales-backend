package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesCommand;
import com.kynsof.calendar.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesMessage;
import com.kynsof.calendar.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesRequest;
import com.kynsof.calendar.application.command.tunerSpecialties.delete.DeleteTurnerSpecialtiesCommand;
import com.kynsof.calendar.application.command.tunerSpecialties.delete.DeleteTurnerSpecialtiesMessage;
import com.kynsof.calendar.application.command.tunerSpecialties.update.UpdateTurnerSpecialtiesCommand;
import com.kynsof.calendar.application.command.tunerSpecialties.update.UpdateTurnerSpecialtiesMessage;
import com.kynsof.calendar.application.command.tunerSpecialties.update.UpdateTurnerSpecialtiesRequest;
import com.kynsof.calendar.application.query.TurnerSpecialtiesResponse;
import com.kynsof.calendar.application.query.tunerSpecialties.getById.FindTurnerSpecialtiesByIdQuery;
import com.kynsof.calendar.application.query.tunerSpecialties.search.GetSearchTurnerSpecialtiesQuery;
import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/turner-specialties")
public class TurnerSpecialtiesController {

    private final IMediator mediator;

    public TurnerSpecialtiesController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateTurnerSpecialtiesRequest request) {

        CreateTurnerSpecialtiesCommand createCommand = CreateTurnerSpecialtiesCommand.fromRequest(request);
        CreateTurnerSpecialtiesMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteTurnerSpecialtiesMessage> delete(@PathVariable("id") UUID id) {

        DeleteTurnerSpecialtiesCommand command = new DeleteTurnerSpecialtiesCommand(id);
        DeleteTurnerSpecialtiesMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateTurnerSpecialtiesRequest request) {

        UpdateTurnerSpecialtiesCommand command = UpdateTurnerSpecialtiesCommand.fromRequest(request, id);
        UpdateTurnerSpecialtiesMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindTurnerSpecialtiesByIdQuery query = new FindTurnerSpecialtiesByIdQuery(id);
        TurnerSpecialtiesResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchTurnerSpecialtiesQuery query = new GetSearchTurnerSpecialtiesQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

}
