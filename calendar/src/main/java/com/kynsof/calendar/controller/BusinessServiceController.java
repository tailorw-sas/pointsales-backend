package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.businessService.create.CreateAllBusinessServicesCommand;
import com.kynsof.calendar.application.command.businessService.create.CreateAllBusinessServicesMessage;
import com.kynsof.calendar.application.command.businessService.create.CreateAllBusinessServicesRequest;
import com.kynsof.calendar.application.command.businessService.delete.DeleteBusinessModuleCommand;
import com.kynsof.calendar.application.command.businessService.delete.DeleteBusinessServiceMessage;
import com.kynsof.calendar.application.command.businessService.update.UpdateBusinessServicesCommand;
import com.kynsof.calendar.application.command.businessService.update.UpdateBusinessServicesMessage;
import com.kynsof.calendar.application.command.businessService.update.UpdateBusinessServicesRequest;
import com.kynsof.calendar.application.query.businessService.getbyid.BusinessServicesResponse;
import com.kynsof.calendar.application.query.businessService.getbyid.FindBusinessServiceByIdQuery;
import com.kynsof.calendar.application.query.businessService.getServicesByBusiness.FindServiceByIdBusinessQuery;
import com.kynsof.calendar.application.query.businessService.search.GetSearchBusinessServiceQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/business-services")
public class BusinessServiceController {

    private final IMediator mediator;

    public BusinessServiceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateAllBusinessServicesRequest request)  {
        CreateAllBusinessServicesCommand createCommand = CreateAllBusinessServicesCommand.fromRequest(request);
        CreateAllBusinessServicesMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindBusinessServiceByIdQuery query = new FindBusinessServiceByIdQuery(id);
        BusinessServicesResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/services/{businessId}")
    public ResponseEntity<?> findServicesByBusinessId(@PathVariable UUID businessId) {

        Pageable pageable = PageRequest.of(0, 1000);
        FindServiceByIdBusinessQuery query = new FindServiceByIdBusinessQuery(businessId, pageable);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}/services")
    public ResponseEntity<?> findServicesByBusinessBackendId(@PathVariable UUID id) {

        Pageable pageable = PageRequest.of(0, 1000);
        FindServiceByIdBusinessQuery query = new FindServiceByIdBusinessQuery(id, pageable);
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

    @PatchMapping()
    public ResponseEntity<UpdateBusinessServicesMessage> update( @RequestBody UpdateBusinessServicesRequest request) {

        UpdateBusinessServicesCommand command = UpdateBusinessServicesCommand.fromRequest(request);
        UpdateBusinessServicesMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {

        DeleteBusinessModuleCommand query = new DeleteBusinessModuleCommand(id);
        DeleteBusinessServiceMessage response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

}
