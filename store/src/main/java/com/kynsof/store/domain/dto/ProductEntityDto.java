package com.kynsof.store.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ProductEntityDto {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Double cost;
    private Integer quantityInStock;
    private String status;
    private UUID subcategoryId;
    private UUID supplierId;
    private SupplierEntityDto supplierEntityDto;
    private SubcategoryEntityDto subcategoryEntityDto;
}
