package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.ProductEntityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Integer quantityInStock;
    private String status;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public ProductEntityDto toAggregate() {
        UUID subcategoryId = this.subcategory != null ? this.subcategory.getId() : null;
        UUID supplierId = this.supplier != null ? this.supplier.getId() : null;
        return new ProductEntityDto(this.id, this.name, this.description, this.price, this.quantityInStock, this.status, subcategoryId, supplierId);
    }
}
