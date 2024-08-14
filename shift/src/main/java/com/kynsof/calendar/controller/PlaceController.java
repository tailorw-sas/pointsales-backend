package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.place.create.CreatePlaceCommand;
import com.kynsof.calendar.application.command.place.create.CreatePlaceMessage;
import com.kynsof.calendar.application.command.place.create.CreatePlaceRequest;
import com.kynsof.calendar.application.command.place.delete.DeletePlaceCommand;
import com.kynsof.calendar.application.command.place.delete.DeletePlaceMessage;
import com.kynsof.calendar.application.command.place.update.UpdatePlaceCommand;
import com.kynsof.calendar.application.command.place.update.UpdatePlaceMessage;
import com.kynsof.calendar.application.command.place.update.UpdatePlaceRequest;
import com.kynsof.calendar.application.query.PlaceResponse;
import com.kynsof.calendar.application.query.place.getById.FindPlaceByIdQuery;
import com.kynsof.calendar.application.query.place.search.GetSearchPlaceQuery;
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
@RequestMapping("/api/place")
public class PlaceController {

    private final IMediator mediator;

    public PlaceController(IMediator mediator){

        this.mediator = mediator;
    }
    @PostMapping("")
    public ResponseEntity<CreatePlaceMessage> create(@RequestBody @Valid CreatePlaceRequest request)  {
        CreatePlaceCommand createCommand = CreatePlaceCommand.fromRequest(request);
        CreatePlaceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchPlaceQuery query = new GetSearchPlaceQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PlaceResponse> getById(@PathVariable UUID id) {

        FindPlaceByIdQuery query = new FindPlaceByIdQuery(id);
        PlaceResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletePlaceMessage> deleteServices(@PathVariable("id") UUID id) {

        DeletePlaceCommand command = new DeletePlaceCommand(id);
        DeletePlaceMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdatePlaceMessage> update(@PathVariable("id") UUID id,@RequestBody UpdatePlaceRequest request) {

        UpdatePlaceCommand command = UpdatePlaceCommand.fromRequest(id,request);
        UpdatePlaceMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
