package com.kynsof.store.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.ProductRequest;
import com.kynsof.store.application.command.create.CreateProductCommand;
import com.kynsof.store.application.command.create.CreateProductMessage;
import com.kynsof.store.application.command.update.UpdateProductCommand;
import com.kynsof.store.application.command.update.UpdateProductMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IMediator mediator;

    public ProductController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateProductMessage> createProduct(@RequestBody ProductRequest request) {
        CreateProductCommand createCommand = CreateProductCommand.fromFrontRequest(request);
        CreateProductMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateProductMessage> updateProduct(@PathVariable UUID id, @RequestBody ProductRequest request) {
        UpdateProductCommand createCommand = UpdateProductCommand.fromRequest(id,request);
        UpdateProductMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllProducts() {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<String>> getProductById(@PathVariable UUID id) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<String>> searchProducts(@RequestParam String keyword) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<String>> deleteProduct(@PathVariable UUID id) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }
}
