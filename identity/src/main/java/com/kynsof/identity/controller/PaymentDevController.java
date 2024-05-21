package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.paymentdev.create.CreatePaymentDevCommand;
import com.kynsof.identity.application.command.paymentdev.create.CreatePaymentDevMessage;
import com.kynsof.identity.application.command.paymentdev.create.CreatePaymentDevRequest;
import com.kynsof.identity.application.command.paymentdev.delete.DeletePaymentDevCommand;
import com.kynsof.identity.application.command.paymentdev.delete.DeletePaymentDevMessage;
import com.kynsof.identity.application.command.paymentdev.update.UpdatePaymentDevCommand;
import com.kynsof.identity.application.command.paymentdev.update.UpdatePaymentDevMessage;
import com.kynsof.identity.application.command.paymentdev.update.UpdatePaymentDevRequest;
import com.kynsof.identity.application.query.paymentdev.getbyid.FindPaymentDevByIdQuery;
import com.kynsof.identity.application.query.paymentdev.getbyid.PaymentDevResponse;
import com.kynsof.identity.application.query.paymentdev.search.GetSearchPaymentDevQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchPaymentDevQuery query = new GetSearchPaymentDevQuery(pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {

        DeletePaymentDevCommand command = new DeletePaymentDevCommand(id);
        DeletePaymentDevMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UpdatePaymentDevRequest request) {

        UpdatePaymentDevCommand command = UpdatePaymentDevCommand.fromRequest(request, id);
        UpdatePaymentDevMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
