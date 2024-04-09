package com.kynsof.rrhh.controller;

import com.kynsof.rrhh.application.command.device.create.CreateDeviceCommand;
import com.kynsof.rrhh.application.command.device.create.CreateDeviceMessage;
import com.kynsof.rrhh.application.command.device.create.CreateDeviceRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
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

}
