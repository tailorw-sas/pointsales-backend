package com.kynsof.store.application.command.product.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.product.ProductRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateProductCommand implements ICommand {
    private final UUID productId;
    private final String name;
    private final String description;
    private final Double price;
    private final Double cost;
    private final Integer quantityInStock;
    private final String status;
    private final UUID subcategoryId;
    private final UUID supplierId;

    public UpdateProductCommand(UUID productId, String name, String description, Double price, Double cost, Integer quantityInStock,
                                String status, UUID subcategoryId, UUID supplierId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.cost = cost;
        this.quantityInStock = quantityInStock;
        this.status = status;
        this.subcategoryId = subcategoryId;
        this.supplierId = supplierId;
    }

    public static UpdateProductCommand fromRequest(UUID productId, ProductRequest request) {
        return new UpdateProductCommand(
                productId,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getCost(),
                request.getQuantityInStock(),
                request.getStatus(),
                request.getSubcategoryId(),
                request.getSupplierId());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateProductMessage();
    }
}
