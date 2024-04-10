package com.kynsof.rrhh.controller;

import com.kynsof.rrhh.application.command.device.create.CreateDeviceCommand;
import com.kynsof.rrhh.application.command.device.create.CreateDeviceMessage;
import com.kynsof.rrhh.application.command.device.create.CreateDeviceRequest;
import com.kynsof.rrhh.application.command.device.delete.DeleteDeviceCommand;
import com.kynsof.rrhh.application.command.device.delete.DeleteDeviceMessage;
import com.kynsof.rrhh.application.command.device.update.UpdateDeviceCommand;
import com.kynsof.rrhh.application.command.device.update.UpdateDeviceMessage;
import com.kynsof.rrhh.application.command.device.update.UpdateDeviceRequest;
import com.kynsof.rrhh.application.query.device.getbyid.DeviceResponse;
import com.kynsof.rrhh.application.query.device.getbyid.FindDeviceByIdQuery;
import com.kynsof.rrhh.application.query.device.getusersbyiddevice.FindUsersByIdDeviceQuery;
import com.kynsof.rrhh.application.query.device.search.GetSearchDeviceQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    private final IMediator mediator;

    public DeviceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<CreateDeviceMessage> create(@RequestBody CreateDeviceRequest request)  {
        CreateDeviceCommand createCommand = CreateDeviceCommand.fromRequest(request);
        CreateDeviceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindDeviceByIdQuery query = new FindDeviceByIdQuery(id);
        DeviceResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/users-by-device/{id}")
    public ResponseEntity<?> getUsersByIdDevice(@PathVariable UUID id) {

        Pageable pageable = PageRequest.of(0, 1000);
        FindUsersByIdDeviceQuery query = new FindUsersByIdDeviceQuery(id, pageable);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchDeviceQuery query = new GetSearchDeviceQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteDeviceMessage> delete(@PathVariable("id") UUID id) {

        DeleteDeviceCommand command = new DeleteDeviceCommand(id);
        DeleteDeviceMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateDeviceMessage> update(@PathVariable("id") UUID id,@RequestBody UpdateDeviceRequest request) {

        UpdateDeviceCommand command = UpdateDeviceCommand.fromRequest(request,id);
        UpdateDeviceMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
