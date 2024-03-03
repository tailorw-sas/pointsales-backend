package com.kynsof.store.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.SubcategoryRequest;
import com.kynsof.store.application.command.create.CreateSubcategoryCommand;
import com.kynsof.store.application.command.create.CreateSubcategoryMessage;
import com.kynsof.store.application.command.update.UpdateSubcategoryCommand;
import com.kynsof.store.application.command.update.UpdateSubcategoryMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/subcategories")
public class SubcategoryController {

    private final IMediator mediator;

    public SubcategoryController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateSubcategoryMessage> createSubcategory(@RequestBody SubcategoryRequest request) {
        CreateSubcategoryCommand createCommand = CreateSubcategoryCommand.fromFrontRequest(request);
        CreateSubcategoryMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateSubcategoryMessage> updateSubcategory(@PathVariable UUID id, @RequestBody SubcategoryRequest request) {
        UpdateSubcategoryCommand createCommand = UpdateSubcategoryCommand.fromRequest(id,request);
        UpdateSubcategoryMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllSubcategories() {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<String>> getSubcategoryById(@PathVariable UUID id) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<String>> searchSubcategories(@RequestParam String keyword) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<String>> deleteSubcategory(@PathVariable UUID id) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }
}
