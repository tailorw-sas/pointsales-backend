package com.kynsof.store.application.command.product.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.ProductRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateProductCommand implements ICommand {
    private String name;
    private String description;
    private Double price;
    private Integer quantityInStock;
    private String status;
    private UUID subcategoryId;
    private UUID supplierId;
    private UUID id;

    public CreateProductCommand(String name, String description, Double price, Integer quantityInStock, String status,
                                UUID subcategoryId, UUID supplierId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.status = status;
        this.subcategoryId = subcategoryId;
        this.supplierId = supplierId;
    }

    public static CreateProductCommand fromFrontRequest(ProductRequest request) {
        return new CreateProductCommand(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getQuantityInStock(),
                request.getStatus(),
                request.getSubcategoryId(),
                request.getSupplierId()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateProductMessage(id);
    }
}
