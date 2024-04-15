package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.businessresource.create.CreateBusinessResourceCommand;
import com.kynsof.calendar.application.command.businessresource.create.CreateBusinessResourceMessage;
import com.kynsof.calendar.application.command.businessresource.create.CreateBusinessresourceRequest;
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
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindBusinessResourceByIdQuery query = new FindBusinessResourceByIdQuery(id);
        BusinessResourceResponse response = mediator.send(query);

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

}
