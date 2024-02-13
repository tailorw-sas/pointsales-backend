package com.kynsof.store.infrastructure.entity;

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
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "subcategory")
    private List<Product> products;
}