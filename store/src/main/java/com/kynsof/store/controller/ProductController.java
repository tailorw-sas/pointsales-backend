package com.kynsof.store.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.product.ProductRequest;
import com.kynsof.store.application.command.product.create.CreateProductCommand;
import com.kynsof.store.application.command.product.create.CreateProductMessage;
import com.kynsof.store.application.command.product.delete.DeleteProductCommand;
import com.kynsof.store.application.command.product.delete.DeleteProductMessage;
import com.kynsof.store.application.command.product.update.UpdateProductCommand;
import com.kynsof.store.application.command.product.update.UpdateProductMessage;
import com.kynsof.store.application.query.product.FindProductsByIdQuery;
import com.kynsof.store.application.query.product.GetAllProductsQuery;
import com.kynsof.store.application.query.product.ProductResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
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
        UpdateProductCommand createCommand = UpdateProductCommand.fromRequest(id, request);
        UpdateProductMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllProducts() {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
        FindProductsByIdQuery query = new FindProductsByIdQuery(id);
        ProductResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> searchProducts(@RequestBody SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetAllProductsQuery query = new GetAllProductsQuery(pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DeleteProductMessage> deleteProduct(@PathVariable UUID id) {
        DeleteProductCommand command = new DeleteProductCommand(id);
        DeleteProductMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }
}
