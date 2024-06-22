package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.SubcategoryEntityDto;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    @Column(nullable = true, updatable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    public Subcategory(SubcategoryEntityDto subcategoryDto) {
        this.id = subcategoryDto.getId();
        this.name = subcategoryDto.getName();
        this.description = subcategoryDto.getDescription();
        this.category = new Category(subcategoryDto.getCategory());
    }

    public SubcategoryEntityDto toAggregate() {
        UUID categoryId = this.category != null ? this.category.getId() : null;
        assert category != null;
        return new SubcategoryEntityDto(this.id, this.name, this.description, categoryId, category.toAggregate());
    }
}
