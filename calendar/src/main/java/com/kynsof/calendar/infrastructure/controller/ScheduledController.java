package com.kynsof.calendar.infrastructure.controller;

import com.kynsof.calendar.application.command.schedule.create.CreateScheduleCommand;
import com.kynsof.calendar.application.command.schedule.create.CreateScheduleMessage;
import com.kynsof.calendar.application.command.schedule.create.CreateScheduleRequest;
import com.kynsof.calendar.application.command.schedule.createall.CreateAllScheduleMessage;
import com.kynsof.calendar.application.command.schedule.createall.CreateAllScheduleRequest;
import com.kynsof.calendar.application.command.schedule.createall.CreateScheduleAllCommand;
import com.kynsof.calendar.application.command.schedule.createlote.CreateScheduleByLoteCommand;
import com.kynsof.calendar.application.command.schedule.createlote.CreateScheduleByLoteMessage;
import com.kynsof.calendar.application.command.schedule.createlote.CreateScheduleByLoteRequest;
import com.kynsof.calendar.application.command.schedule.delete.ScheduleDeleteCommand;
import com.kynsof.calendar.application.command.schedule.delete.ScheduleDeleteMessage;
import com.kynsof.calendar.application.command.schedule.update.ScheduleUpdateRequest;
import com.kynsof.calendar.application.command.schedule.update.UpdateScheduleCommand;
import com.kynsof.calendar.application.command.schedule.update.UpdateScheduleMessage;
import com.kynsof.calendar.application.query.ScheduleResponse;
import com.kynsof.calendar.application.query.schedule.getAll.FindScheduleWithFilterQuery;
import com.kynsof.calendar.application.query.schedule.getAvailableDatesAndSlots.AvailableDatesByResourceRequest;
import com.kynsof.calendar.application.query.schedule.getAvailableDatesAndSlots.GetAvailableDatesAndSlotsQuery;
import com.kynsof.calendar.application.query.schedule.getAvailableDatesAndSlots.GetAvailableDatesAndSlotsResponse;
import com.kynsof.calendar.application.query.schedule.getAvailableDatesByServiceId.AvailableDatesRequest;
import com.kynsof.calendar.application.query.schedule.getAvailableDatesByServiceId.GetAvailableDatesByServiceIdQuery;
import com.kynsof.calendar.application.query.schedule.getAvailableDatesByServiceId.GetAvailableDatesByServiceIdResponse;
import com.kynsof.calendar.application.query.schedule.getbyid.FindScheduleByIdQuery;
import com.kynsof.calendar.application.query.schedule.search.GetSearchScheduleQuery;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/scheduled")
public class ScheduledController {

    private final IMediator mediator;

    public ScheduledController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateScheduleMessage> create(@RequestBody CreateScheduleRequest request) {
        CreateScheduleCommand createCommand = CreateScheduleCommand.fromRequest(request);
        CreateScheduleMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-all")
    public ResponseEntity<CreateAllScheduleMessage> create(@RequestBody CreateAllScheduleRequest request) throws Exception {

        CreateAllScheduleMessage response = mediator.send(new CreateScheduleAllCommand(
                request.getResourceId(),
                request.getBusinessId(),
                request.getServiceId(),
                request.getDate(),
                request.getSchedules(),
                mediator
        ));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-lote")
    public ResponseEntity<CreateScheduleByLoteMessage> create(@RequestBody CreateScheduleByLoteRequest request) throws Exception {

        CreateScheduleByLoteMessage response = mediator.send(new CreateScheduleByLoteCommand(
                request.getResourceId(), 
                request.getBusinessId(), 
                request.getServiceId(), 
                request.getStartDate(), 
                request.getEndDate(), 
                request.getSchedules(), 
                mediator
        ));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") UUID resource,
            @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(defaultValue = "") EStatusSchedule status) {
        Pageable pageable = PageRequest.of(page, pageSize);
        FindScheduleWithFilterQuery query = new FindScheduleWithFilterQuery(pageable, resource, date, startDate, endDate, status);
        PaginatedResponse data = mediator.send(query);

        return ResponseEntity.ok(data);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchScheduleQuery query = new GetSearchScheduleQuery(pageable, request.getFilter(), request.getQuery());
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

    @PutMapping()
    public ResponseEntity<UpdateScheduleMessage> update(@RequestBody ScheduleUpdateRequest request) {

        UpdateScheduleCommand command = UpdateScheduleCommand.fromRequest(request);
        UpdateScheduleMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/available-dates")
    public ResponseEntity<GetAvailableDatesByServiceIdResponse> getAvailableDatesByServiceId(@RequestBody AvailableDatesRequest request) {
        GetAvailableDatesByServiceIdQuery query = new GetAvailableDatesByServiceIdQuery(request.getServiceId(),
                request.getStartDate(), request.getFinalDate());
        GetAvailableDatesByServiceIdResponse availableDates = mediator.send(query);
        return ResponseEntity.ok(availableDates);
    }

    @PostMapping("/{resourceId}/available-dates")
    public ResponseEntity<?> getAvailableDatesAndSlotsQuery( @PathVariable UUID resourceId, @RequestBody AvailableDatesByResourceRequest request) {
        GetAvailableDatesAndSlotsQuery query = new GetAvailableDatesAndSlotsQuery( resourceId, request.getBusinessId(),
                request.getStartDate(), request.getFinalDate());
        GetAvailableDatesAndSlotsResponse availableDates = mediator.send(query);
        return ResponseEntity.ok(availableDates);
    }
}
