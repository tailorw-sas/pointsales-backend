package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.command.service.create.CreateServiceCommand;
import com.kynsof.scheduled.application.command.service.create.CreateServiceMessage;
import com.kynsof.scheduled.application.command.service.create.CreateServiceRequest;
import com.kynsof.scheduled.application.query.ServicesResponse;
import com.kynsof.scheduled.application.query.service.getbyid.FindServiceByIdQuery;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    private final IMediator mediator;

    public ServiceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateServiceMessage> create(@RequestBody CreateServiceRequest request)  {
        CreateServiceCommand createCommand = CreateServiceCommand.fromRequest(request);
        CreateServiceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ServicesResponse> getById(@PathVariable UUID id) {

        FindServiceByIdQuery query = new FindServiceByIdQuery(id);
        ServicesResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

//
//    @GetMapping("/all")
//    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
//                                                    @RequestParam(defaultValue = "0") Integer page,
//                                                    @RequestParam(defaultValue = "") UUID idService,
//                                                    @RequestParam(defaultValue = "") String filter)
//    {
//        Pageable pageable = PageRequest.of(page, pageSize);
//        FindServiceByIdQuery query = new FindBusinessWithFilterQuery(pageable, idBusiness, filter);
//        PaginatedResponse data = mediator.send(query);
//
//        return ResponseEntity.ok(data);
//    }

}
