package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.command.business.create.CreateBusinessCommand;
import com.kynsof.scheduled.application.command.business.create.CreateBusinessMessage;
import com.kynsof.scheduled.application.command.business.create.CreateBusinessRequest;
import com.kynsof.scheduled.application.command.schedule.create.CreateScheduleCommand;
import com.kynsof.scheduled.application.command.schedule.create.CreateScheduleMessage;
import com.kynsof.scheduled.application.command.schedule.create.CreateScheduleRequest;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final IMediator mediator;

    public ScheduleController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateScheduleMessage> create(@RequestBody CreateScheduleRequest request)  {
        CreateScheduleCommand createCommand = CreateScheduleCommand.fromRequest(request);
        CreateScheduleMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

}
