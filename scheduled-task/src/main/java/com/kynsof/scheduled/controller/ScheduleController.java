package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.command.schedule.create.CreateScheduleCommand;
import com.kynsof.scheduled.application.command.schedule.create.CreateScheduleMessage;
import com.kynsof.scheduled.application.command.schedule.create.CreateScheduleRequest;
import com.kynsof.scheduled.application.command.schedule.createall.CreateAllScheduleCommand;
import com.kynsof.scheduled.application.command.schedule.createall.ScheduleCreateAllRequest;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/create/all")
    public ResponseEntity<CreateScheduleMessage> create(@RequestBody ScheduleCreateAllRequest request) throws Exception {

        CreateAllScheduleCommand createCommand = CreateAllScheduleCommand.fromRequest(request);
        CreateScheduleMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

}
