package com.kynsof.store.application.command.subcategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoryRequest {
    private String name;
    private String description;
    private UUID categoryId;
}