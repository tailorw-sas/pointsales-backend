package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.businessresource.create.CreateBusinessResourceCommand;
import com.kynsof.calendar.application.command.businessresource.create.CreateBusinessResourceMessage;
import com.kynsof.calendar.application.command.businessresource.create.CreateBusinessresourceRequest;
import com.kynsof.calendar.application.command.businessresource.delete.DeleteBusinessResourceCommand;
import com.kynsof.calendar.application.command.businessresource.delete.DeleteBusinessResourceMessage;
import com.kynsof.calendar.application.command.businessresource.delete.DeleteBusinessResourceRequest;
import com.kynsof.calendar.application.command.businessresource.update.UpdateBusinessResourceCommand;
import com.kynsof.calendar.application.command.businessresource.update.UpdateBusinessResourceMessage;
import com.kynsof.calendar.application.command.businessresource.update.UpdateBusinessresourceRequest;
import com.kynsof.calendar.application.command.businessservices.createall.CreateAllBusinessServicesCommand;
import com.kynsof.calendar.application.command.businessservices.createall.CreateAllBusinessServicesMessage;
import com.kynsof.calendar.application.command.businessservices.createall.CreateAllBusinessServicesRequest;
import com.kynsof.calendar.application.query.businesservice.getresourcebybusiness.FindResourcesByIdBusinessQuery;
import com.kynsof.calendar.application.query.businessresource.getbyid.BusinessResourceResponse;
import com.kynsof.calendar.application.query.businessresource.getbyid.FindBusinessResourceByIdQuery;
import com.kynsof.calendar.application.query.businessresource.search.GetSearchBusinessResourceQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/business-resource")
public class BusinessResourceController {

    private final IMediator mediator;

    public BusinessResourceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateBusinessresourceRequest request)  {
        CreateBusinessResourceCommand createCommand = CreateBusinessResourceCommand.fromRequest(request);
        CreateBusinessResourceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody DeleteBusinessResourceRequest request)  {
        DeleteBusinessResourceCommand createCommand = DeleteBusinessResourceCommand.fromRequest(request);
        DeleteBusinessResourceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindBusinessResourceByIdQuery query = new FindBusinessResourceByIdQuery(id);
        BusinessResourceResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/resources/{id}")
    public ResponseEntity<?> findResourcesByBusinessId(@PathVariable UUID id) {

        Pageable pageable = PageRequest.of(0, 1000);
        FindResourcesByIdBusinessQuery query = new FindResourcesByIdBusinessQuery(id, pageable);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchBusinessResourceQuery query = new GetSearchBusinessResourceQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateBusinessResourceMessage> update(@PathVariable("id") UUID id, @RequestBody UpdateBusinessresourceRequest request) {

        UpdateBusinessResourceCommand command = UpdateBusinessResourceCommand.fromRequest(request, id);
        UpdateBusinessResourceMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
