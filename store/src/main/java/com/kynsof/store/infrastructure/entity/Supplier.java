package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.SupplierEntityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String address;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    public SupplierEntityDto toAggregate() {
        return new SupplierEntityDto(this.id, this.name, this.address, this.phone, this.email);
    }
}
