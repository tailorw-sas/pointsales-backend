package com.kynsof.store.application.query.getAll;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.store.domain.dto.CategoryEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class CategoryResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;

    public CategoryResponse(CategoryEntityDto categoryDto) {
        this.id = categoryDto.getId();
        this.name = categoryDto.getName();
        this.description = categoryDto.getDescription();
    }
}
