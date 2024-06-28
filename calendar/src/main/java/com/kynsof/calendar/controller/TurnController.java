package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.turn.create.CreateTurnCommand;
import com.kynsof.calendar.application.command.turn.create.CreateTurnMessage;
import com.kynsof.calendar.application.command.turn.create.CreateTurnRequest;
import com.kynsof.calendar.application.command.turn.delete.DeleteTurnCommand;
import com.kynsof.calendar.application.command.turn.delete.DeleteTurnMessage;
import com.kynsof.calendar.application.command.turn.update.UpdateTurnCommand;
import com.kynsof.calendar.application.command.turn.update.UpdateTurnMessage;
import com.kynsof.calendar.application.command.turn.update.UpdateTurnRequest;
import com.kynsof.calendar.application.query.TurnResponse;
import com.kynsof.calendar.application.query.turn.getbyid.FindTurnByIdQuery;
import com.kynsof.calendar.application.query.turn.search.GetSearchTurnQuery;
import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/turn")
public class TurnController {

    private final IMediator mediator;

    public TurnController(IMediator mediator){

        this.mediator = mediator;
    }
    @PostMapping("")
    public ResponseEntity<CreateTurnMessage> create(@RequestBody @Valid CreateTurnRequest request)  {
        CreateTurnCommand createCommand = CreateTurnCommand.fromRequest(request);
        CreateTurnMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchTurnQuery query = new GetSearchTurnQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TurnResponse> getById(@PathVariable UUID id) {

        FindTurnByIdQuery query = new FindTurnByIdQuery(id);
        TurnResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteTurnMessage> deleteServices(@PathVariable("id") UUID id) {

        DeleteTurnCommand command = new DeleteTurnCommand(id);
        DeleteTurnMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateTurnMessage> update(@PathVariable("id") UUID id,@RequestBody UpdateTurnRequest request) {

        UpdateTurnCommand command = UpdateTurnCommand.fromRequest(id,request);
        UpdateTurnMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
