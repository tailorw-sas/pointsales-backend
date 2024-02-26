package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.resource.create.CreateResourceCommand;
import com.kynsof.calendar.application.command.resource.create.CreateResourceMessage;
import com.kynsof.calendar.application.command.resource.create.CreateResourceRequest;
import com.kynsof.calendar.application.command.resource.delete.ResourceDeleteCommand;
import com.kynsof.calendar.application.command.resource.delete.ResourceDeleteMessage;
import com.kynsof.calendar.application.command.resource.update.UpdateResourceCommand;
import com.kynsof.calendar.application.command.resource.update.UpdateResourceMessage;
import com.kynsof.calendar.application.command.resource.update.UpdateResourceRequest;
import com.kynsof.calendar.application.query.ResourceResponse;
import com.kynsof.calendar.application.query.resource.getAll.FindResourceWithFilterQuery;
import com.kynsof.calendar.application.query.resource.getbyid.FindResourceByIdQuery;
import com.kynsof.calendar.application.query.resource.search.GetSearchResourceQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    private final IMediator mediator;

    public ResourceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateResourceMessage> create(@RequestBody CreateResourceRequest request)  {
        CreateResourceCommand createCommand = CreateResourceCommand.fromRequest(request);
        CreateResourceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResourceResponse> getById(@PathVariable UUID id) {

        FindResourceByIdQuery query = new FindResourceByIdQuery(id);
        ResourceResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID idBusiness,
                                                    @RequestParam(defaultValue = "") String filter)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        FindResourceWithFilterQuery query = new FindResourceWithFilterQuery(pageable, idBusiness, filter);
        PaginatedResponse data = mediator.send(query);

        return ResponseEntity.ok(data);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchResourceQuery query = new GetSearchResourceQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceDeleteMessage> delete(@PathVariable("id") UUID id) {

        ResourceDeleteCommand command = new ResourceDeleteCommand(id);
        ResourceDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<UpdateResourceMessage> update(@RequestBody UpdateResourceRequest request) {

        UpdateResourceCommand command = UpdateResourceCommand.fromRequest(request);
        UpdateResourceMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
