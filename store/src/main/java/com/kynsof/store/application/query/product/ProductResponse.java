package com.kynsof.store.application.query.product;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.store.domain.dto.ProductEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ProductResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Integer quantityInStock;
    private String status;
    private UUID subcategoryId;
    private UUID supplierId;

    public ProductResponse(ProductEntityDto productDto) {
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.quantityInStock = productDto.getQuantityInStock();
        this.status = productDto.getStatus();
        this.subcategoryId = productDto.getSubcategoryId();
        this.supplierId = productDto.getSupplierId();
    }
}

