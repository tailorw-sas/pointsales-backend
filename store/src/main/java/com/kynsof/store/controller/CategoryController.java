package com.kynsof.store.controller;


import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.CategoryRequest;
import com.kynsof.store.application.command.create.CreateCategoryCommand;
import com.kynsof.store.application.command.create.CreateCategoryMessage;
import com.kynsof.store.application.command.update.UpdateCategoryCommand;
import com.kynsof.store.application.command.update.UpdateCategoryMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final IMediator mediator;

    public CategoryController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateCategoryMessage> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CreateCategoryCommand createCommand = CreateCategoryCommand.fromRequest(categoryRequest);
        CreateCategoryMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateCategoryMessage> updateCategory(@PathVariable UUID id, @RequestBody CategoryRequest categoryRequest) {
        UpdateCategoryCommand updateCategoryCommand = UpdateCategoryCommand.fromRequest(id,categoryRequest);
        UpdateCategoryMessage response = mediator.send(updateCategoryCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllCategories() {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<String>> getCategoryById(@PathVariable UUID id) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<String>> searchCategories(@RequestParam String keyword) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<String>> deleteCategory(@PathVariable UUID id) {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }
}
