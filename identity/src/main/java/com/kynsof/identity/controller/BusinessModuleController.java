package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.businessmodule.create.CreateBusinessModuleCommand;
import com.kynsof.identity.application.command.businessmodule.create.CreateBusinessModuleMessage;
import com.kynsof.identity.application.command.businessmodule.create.CreateBusinessModuleRequest;
import com.kynsof.identity.application.query.businessmodule.getbyid.FindBusinessModuleByIdQuery;
import com.kynsof.identity.application.query.businessmodule.search.BusinessModuleResponse;
import com.kynsof.identity.application.query.businessmodule.search.GetSearchBusinessModuleQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/business-module")
public class BusinessModuleController {

    private final IMediator mediator;

    public BusinessModuleController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<CreateBusinessModuleMessage> create(@RequestBody CreateBusinessModuleRequest request)  {
        CreateBusinessModuleCommand createCommand = CreateBusinessModuleCommand.fromRequest(request);
        CreateBusinessModuleMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BusinessModuleResponse> getById(@PathVariable UUID id) {

        FindBusinessModuleByIdQuery query = new FindBusinessModuleByIdQuery(id);
        BusinessModuleResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchBusinessModuleQuery query = new GetSearchBusinessModuleQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

}
