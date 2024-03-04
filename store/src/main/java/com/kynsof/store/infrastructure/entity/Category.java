package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.CategoryDto;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories;

    public Category(CategoryDto categoryDto) {
        this.id = categoryDto.getId();
        this.name = categoryDto.getName();
        this.description = categoryDto.getDescription();
    }

    public CategoryDto toAggregate() {
        return new CategoryDto(this.id, this.name, this.description);
    }
}
