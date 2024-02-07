package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.command.receipt.create.CreateReceiptCommand;
import com.kynsof.scheduled.application.command.receipt.create.CreateReceiptMessage;
import com.kynsof.scheduled.application.command.receipt.create.CreateReceiptRequest;
import com.kynsof.scheduled.application.query.ReceiptResponse;
import com.kynsof.scheduled.application.query.business.getbyid.FindBusinessByIdQuery;
import com.kynsof.scheduled.application.query.receipt.getbyid.FindReceiptByIdQuery;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import java.util.UUID;
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

}
