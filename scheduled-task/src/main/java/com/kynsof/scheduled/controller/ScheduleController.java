package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.command.schedule.create.CreateScheduleCommand;
import com.kynsof.scheduled.application.command.schedule.create.CreateScheduleMessage;
import com.kynsof.scheduled.application.command.schedule.create.CreateScheduleRequest;
import com.kynsof.scheduled.application.command.schedule.createall.CreateScheduleAllCommand;
import com.kynsof.scheduled.application.command.schedule.createall.CreateScheduleAllRequest;
import com.kynsof.scheduled.application.command.schedule.createlote.CreateScheduleByLoteCommand;
import com.kynsof.scheduled.application.command.schedule.createlote.CreateScheduleByLoteRequest;
import com.kynsof.scheduled.application.command.schedule.delete.ScheduleDeleteCommand;
import com.kynsof.scheduled.application.command.schedule.delete.ScheduleDeleteMessage;
import com.kynsof.scheduled.application.query.ScheduleResponse;
import com.kynsof.scheduled.application.query.schedule.getAll.FindScheduleWithFilterQuery;
import com.kynsof.scheduled.application.query.schedule.getbyid.FindScheduleByIdQuery;
import com.kynsof.scheduled.domain.dto.EStatusSchedule;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<CreateScheduleMessage> create(@RequestBody CreateScheduleAllRequest request) throws Exception {

        CreateScheduleAllCommand createCommand = CreateScheduleAllCommand.fromRequest(request);
        CreateScheduleMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create/lote")
    public ResponseEntity<CreateScheduleMessage> create(@RequestBody CreateScheduleByLoteRequest request) throws Exception {

        CreateScheduleByLoteCommand createCommand = CreateScheduleByLoteCommand.fromRequest(request);
        CreateScheduleMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID resource,
                                                    @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                    @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                    @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                    @RequestParam(defaultValue = "") EStatusSchedule status)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        FindScheduleWithFilterQuery query = new FindScheduleWithFilterQuery(pageable, resource, date, startDate, endDate, status);
        PaginatedResponse data = mediator.send(query);

        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getById(@PathVariable UUID id) {
        FindScheduleByIdQuery query = new FindScheduleByIdQuery(id);
        ScheduleResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleDeleteMessage> delete(@PathVariable("id") UUID id) {

        ScheduleDeleteCommand command = new ScheduleDeleteCommand(id);
        ScheduleDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

}
