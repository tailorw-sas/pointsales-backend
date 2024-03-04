package com.kynsof.store.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class SubcategoryEntityDto {
    private UUID id;
    private String name;
    private String description;
    private UUID categoryId;
    private CategoryDto category;
}
