package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.businessservices.create.CreateBusinessServicesCommand;
import com.kynsof.calendar.application.command.businessservices.create.CreateBusinessServicesMessage;
import com.kynsof.calendar.application.command.businessservices.create.CreateBusinessServicesRequest;
import com.kynsof.calendar.application.command.businessservices.createall.CreateAllBusinessServicesCommand;
import com.kynsof.calendar.application.command.businessservices.createall.CreateAllBusinessServicesMessage;
import com.kynsof.calendar.application.command.businessservices.createall.CreateAllBusinessServicesRequest;
import com.kynsof.calendar.application.command.businessservices.update.UpdateBusinessServicesCommand;
import com.kynsof.calendar.application.command.businessservices.update.UpdateBusinessServicesMessage;
import com.kynsof.calendar.application.command.businessservices.update.UpdateBusinessServicesRequest;
import com.kynsof.calendar.application.query.businesservice.getbyid.BusinessServicesResponse;
import com.kynsof.calendar.application.query.businesservice.getbyid.FindBusinessServiceByIdQuery;
import com.kynsof.calendar.application.query.businesservice.getservicesbybusiness.FindServiceByIdBusinessQuery;
import com.kynsof.calendar.application.query.businesservice.getservicesbyresources.FindServiceByIdResourcesQuery;
import com.kynsof.calendar.application.query.businesservice.search.GetSearchBusinessServiceQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/business-services")
public class BusinessServiceController {

    private final IMediator mediator;

    public BusinessServiceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateBusinessServicesRequest request)  {
        CreateBusinessServicesCommand createCommand = CreateBusinessServicesCommand.fromRequest(request);
        CreateBusinessServicesMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-all-services")
    public ResponseEntity<?> createAllServices(@RequestBody CreateAllBusinessServicesRequest request)  {
        CreateAllBusinessServicesCommand createCommand = CreateAllBusinessServicesCommand.fromRequest(request, this.mediator);
        CreateAllBusinessServicesMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindBusinessServiceByIdQuery query = new FindBusinessServiceByIdQuery(id);
        BusinessServicesResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/services/{id}")
    public ResponseEntity<?> findServicesByBusinessId(@PathVariable UUID id) {

        Pageable pageable = PageRequest.of(0, 1000);
        FindServiceByIdBusinessQuery query = new FindServiceByIdBusinessQuery(id, pageable);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/services-by-resource/{id}")
    public ResponseEntity<?> findServicesByResourceId(@PathVariable UUID id) {

        Pageable pageable = PageRequest.of(0, 1000);
        FindServiceByIdResourcesQuery query = new FindServiceByIdResourcesQuery(id, pageable);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchBusinessServiceQuery query = new GetSearchBusinessServiceQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateBusinessServicesMessage> update(@PathVariable("id") UUID id, @RequestBody UpdateBusinessServicesRequest request) {

        UpdateBusinessServicesCommand command = UpdateBusinessServicesCommand.fromRequest(request, id);
        UpdateBusinessServicesMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
