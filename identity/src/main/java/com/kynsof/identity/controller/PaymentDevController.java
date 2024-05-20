package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.paymentdev.create.CreatePaymentDevCommand;
import com.kynsof.identity.application.command.paymentdev.create.CreatePaymentDevMessage;
import com.kynsof.identity.application.command.paymentdev.create.CreatePaymentDevRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-dev")
public class PaymentDevController {

    private final IMediator mediator;

    public PaymentDevController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreatePaymentDevRequest request)  {
        CreatePaymentDevCommand createCommand = CreatePaymentDevCommand.fromRequest(request);
        CreatePaymentDevMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

}
