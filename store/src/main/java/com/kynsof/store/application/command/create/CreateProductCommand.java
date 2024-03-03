package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
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

    @Override
    public ICommandMessage getMessage() {
        // Implementar lógica para obtener el mensaje de comando correspondiente
        return null;
    }
}
