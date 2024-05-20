package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.paymentdev.create.CreatePaymentDevCommand;
import com.kynsof.identity.application.command.paymentdev.create.CreatePaymentDevMessage;
import com.kynsof.identity.application.command.paymentdev.create.CreatePaymentDevRequest;
import com.kynsof.identity.application.query.paymentdev.getbyid.FindPaymentDevByIdQuery;
import com.kynsof.identity.application.query.paymentdev.getbyid.PaymentDevResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindPaymentDevByIdQuery query = new FindPaymentDevByIdQuery(id);
        PaymentDevResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

}
