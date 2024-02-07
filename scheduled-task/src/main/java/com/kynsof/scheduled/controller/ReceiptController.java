package com.kynsof.scheduled.controller;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.command.business.create.CreateBusinessCommand;
import com.kynsof.scheduled.application.command.business.create.CreateBusinessMessage;
import com.kynsof.scheduled.application.command.business.create.CreateBusinessRequest;
import com.kynsof.scheduled.application.command.business.delete.BusinessDeleteCommand;
import com.kynsof.scheduled.application.command.business.delete.BusinessDeleteMessage;
import com.kynsof.scheduled.application.command.business.update.UpdateBusinessCommand;
import com.kynsof.scheduled.application.command.business.update.UpdateBusinessMessage;
import com.kynsof.scheduled.application.command.business.update.UpdateBusinessRequest;
import com.kynsof.scheduled.application.command.receipt.create.CreateReceiptCommand;
import com.kynsof.scheduled.application.command.receipt.create.CreateReceiptMessage;
import com.kynsof.scheduled.application.command.receipt.create.CreateReceiptRequest;
import com.kynsof.scheduled.application.query.BusinessResponse;
import com.kynsof.scheduled.application.query.business.getAll.FindBusinessWithFilterQuery;
import com.kynsof.scheduled.application.query.business.getbyid.FindBusinessByIdQuery;

import com.kynsof.scheduled.infrastructure.config.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
}
