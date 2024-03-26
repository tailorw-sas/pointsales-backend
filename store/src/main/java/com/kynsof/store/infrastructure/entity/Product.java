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
    private Double cost;
    private Integer quantityInStock;
    private String status;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Product(ProductEntityDto productDto) {
        this.id = productDto.getId(); // Asumiendo que se permite establecer el ID directamente. Si no, omitir para generación automática.
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.cost = productDto.getCost();
        this.quantityInStock = productDto.getQuantityInStock();
        this.status = productDto.getStatus();
        this.supplier = new Supplier(productDto.getSupplierEntityDto());
        this.subcategory = new Subcategory(productDto.getSubcategoryEntityDto());
    }

    public ProductEntityDto toAggregate() {
        UUID subcategoryId = this.subcategory != null ? this.subcategory.getId() : null;
        UUID supplierId = this.supplier != null ? this.supplier.getId() : null;
        assert this.supplier != null;
        return new ProductEntityDto(this.id, this.name, this.description, this.price,cost,
                this.quantityInStock, this.status,
                subcategoryId, supplierId,
                this.supplier.toAggregate(),this.subcategory.toAggregate());
    }
}
