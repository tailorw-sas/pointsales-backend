package com.kynsof.rrhh.controller;

import com.kynsof.rrhh.application.query.business.getbyid.BusinessResponse;
import com.kynsof.rrhh.application.query.business.getbyid.FindBusinessByIdQuery;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/business")
public class BusinessController {

    private final IMediator mediator;

    public BusinessController(IMediator mediator){

        this.mediator = mediator;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BusinessResponse> getById(@PathVariable UUID id) {

        FindBusinessByIdQuery query = new FindBusinessByIdQuery(id);
        BusinessResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

}
