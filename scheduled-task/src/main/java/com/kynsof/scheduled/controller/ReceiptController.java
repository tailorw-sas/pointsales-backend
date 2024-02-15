package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.command.receipt.create.CreateReceiptCommand;
import com.kynsof.scheduled.application.command.receipt.create.CreateReceiptMessage;
import com.kynsof.scheduled.application.command.receipt.create.CreateReceiptRequest;
import com.kynsof.scheduled.application.command.receipt.delete.ReceiptDeleteCommand;
import com.kynsof.scheduled.application.command.receipt.delete.ReceiptDeleteMessage;
import com.kynsof.scheduled.application.query.ReceiptResponse;
import com.kynsof.scheduled.application.query.receipt.getAll.FindReceiptWithFilterQuery;
import com.kynsof.scheduled.application.query.receipt.getbyid.FindReceiptByIdQuery;
import com.kynsof.scheduled.domain.dto.EStatusReceipt;
import com.kynsof.share.core.infrastructure.bus.IMediator;

import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

    private final IMediator mediator;

    public ReceiptController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateReceiptMessage> create(@RequestBody CreateReceiptRequest request)  {
        CreateReceiptCommand createCommand = CreateReceiptCommand.fromRequest(request);
        CreateReceiptMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReceiptResponse> getById(@PathVariable UUID id) {

        FindReceiptByIdQuery query = new FindReceiptByIdQuery(id);
        ReceiptResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "5") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") String filter,
                                                    @RequestParam(defaultValue = "") UUID specialist,
                                                    @RequestParam(defaultValue = "") UUID user,
                                                    @RequestParam(defaultValue = "") UUID service,
                                                    @RequestParam(defaultValue = "") UUID schedule,
                                                    @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                    @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                    @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                    @RequestParam(defaultValue = "") EStatusReceipt status,
                                                    @RequestParam(defaultValue = "asc") String sortType)
    {
        String sortField = "schedule.date";
        Sort sort = (sortType.equals("asc")) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        FindReceiptWithFilterQuery query = new FindReceiptWithFilterQuery(pageable, service, user, service, schedule, date, startDate, endDate, status, filter);
        PaginatedResponse data = mediator.send(query);

        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReceiptDeleteMessage> delete(@PathVariable("id") UUID id) {

        ReceiptDeleteCommand command = new ReceiptDeleteCommand(id);
        ReceiptDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

}
