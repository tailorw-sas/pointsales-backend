package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.command.resource.create.CreateResourceCommand;
import com.kynsof.scheduled.application.command.resource.create.CreateResourceMessage;
import com.kynsof.scheduled.application.command.resource.create.CreateResourceRequest;
import com.kynsof.scheduled.application.command.resource.delete.ResourceDeleteCommand;
import com.kynsof.scheduled.application.command.resource.delete.ResourceDeleteMessage;
import com.kynsof.scheduled.application.query.ResourceResponse;
import com.kynsof.scheduled.application.query.resource.getAll.FindResourceWithFilterQuery;
import com.kynsof.scheduled.application.query.resource.getbyid.FindResourceByIdQuery;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceDeleteMessage> delete(@PathVariable("id") UUID id) {

        ResourceDeleteCommand command = new ResourceDeleteCommand(id);
        ResourceDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

}
