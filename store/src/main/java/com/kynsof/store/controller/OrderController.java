package com.kynsof.store.controller;


import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.OrderRequest;
import com.kynsof.store.application.command.create.CreateOrderCommand;
import com.kynsof.store.application.command.create.CreateOrderMessage;
import com.kynsof.store.application.command.update.UpdateOrderCommand;
import com.kynsof.store.application.command.update.UpdateOrderMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final IMediator mediator;

    public OrderController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateOrderMessage> createOrder(@RequestBody OrderRequest request) {
        CreateOrderCommand createCommand = CreateOrderCommand.fromFrontRequest(request);
        CreateOrderMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateOrderMessage> updateOrder(@PathVariable UUID id, @RequestBody OrderRequest request) {
        UpdateOrderCommand createCommand = UpdateOrderCommand.fromRequest(id, request);
        UpdateOrderMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllOrders() {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<String>> getOrderById(@PathVariable UUID id) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<String>> searchOrders(@RequestParam String keyword) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<String>> deleteOrder(@PathVariable UUID id) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }
}
