package com.kynsof.store.application.query.getAll;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.store.domain.dto.SubcategoryEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class SubcategoryResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;
    private UUID categoryId;

    public SubcategoryResponse(SubcategoryEntityDto subcategoryDto) {
        this.id = subcategoryDto.getId();
        this.name = subcategoryDto.getName();
        this.description = subcategoryDto.getDescription();
        this.categoryId = subcategoryDto.getCategoryId();
    }
}

