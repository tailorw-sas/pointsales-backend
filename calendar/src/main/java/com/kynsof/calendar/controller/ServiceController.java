package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.PaginatedResponse;
import com.kynsof.calendar.application.command.service.create.CreateServiceCommand;
import com.kynsof.calendar.application.command.service.create.CreateServiceMessage;
import com.kynsof.calendar.application.command.service.create.CreateServiceRequest;
import com.kynsof.calendar.application.command.service.delete.ServiceDeleteCommand;
import com.kynsof.calendar.application.command.service.delete.ServiceDeleteMessage;
import com.kynsof.calendar.application.command.service.update.UpdateServiceCommand;
import com.kynsof.calendar.application.command.service.update.UpdateServiceMessage;
import com.kynsof.calendar.application.command.service.update.UpdateServiceRequest;
import com.kynsof.calendar.application.query.ServicesResponse;
import com.kynsof.calendar.application.query.service.getAll.FindServiceWithFilterQuery;
import com.kynsof.calendar.application.query.service.getbyid.FindServiceByIdQuery;
import com.kynsof.share.core.infrastructure.bus.IMediator;

import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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


    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID idService,
                                                    @RequestParam(defaultValue = "") String filter)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        FindServiceWithFilterQuery query = new FindServiceWithFilterQuery(pageable, idService, filter);
        PaginatedResponse data = mediator.send(query);

        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceDeleteMessage> delete(@PathVariable("id") UUID id) {

        ServiceDeleteCommand command = new ServiceDeleteCommand(id);
        ServiceDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<UpdateServiceMessage> update(@RequestBody UpdateServiceRequest request) {

        UpdateServiceCommand command = UpdateServiceCommand.fromRequest(request);
        UpdateServiceMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
