package com.kynsof.store.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.SupplierRequest;
import com.kynsof.store.application.command.create.CreateSupplierCommand;
import com.kynsof.store.application.command.create.CreateSupplierMessage;
import com.kynsof.store.application.command.update.UpdateSupplierCommand;
import com.kynsof.store.application.command.update.UpdateSupplierMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    private final IMediator mediator;

    public SupplierController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateSupplierMessage> createSupplier(@RequestBody SupplierRequest request) {
        CreateSupplierCommand createCommand = CreateSupplierCommand.fromFrontRequest(request);
        CreateSupplierMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateSupplierMessage> updateSupplier(@PathVariable UUID id, @RequestBody SupplierRequest request) {
        UpdateSupplierCommand createCommand = UpdateSupplierCommand.fromRequest(id,request);
        UpdateSupplierMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllSuppliers() {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<String>> getSupplierById(@PathVariable UUID id) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<String>> searchSuppliers(@RequestParam String keyword) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<String>> deleteSupplier(@PathVariable UUID id) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }
}
