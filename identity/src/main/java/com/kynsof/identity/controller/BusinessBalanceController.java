package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.businessBalance.create.CreateBusinessBalanceCommand;
import com.kynsof.identity.application.command.businessBalance.create.CreateBusinessBalanceMessage;
import com.kynsof.identity.application.command.businessBalance.create.CreateBusinessBalanceRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/business-balance")
public class BusinessBalanceController {
    private final IMediator mediator;

    public BusinessBalanceController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<?> addBalance(@RequestBody CreateBusinessBalanceRequest request) {
        CreateBusinessBalanceCommand createCommand = CreateBusinessBalanceCommand.fromRequest(request);
        CreateBusinessBalanceMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/discount")
    public ResponseEntity<?> discountBalance(@RequestBody CreateBusinessBalanceRequest request) {
        CreateBusinessBalanceCommand createCommand = CreateBusinessBalanceCommand.fromRequest(request);
        CreateBusinessBalanceMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }
}
