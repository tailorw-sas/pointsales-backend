package com.kynsof.store.application.command.subcategory.update;


import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.subcategory.SubcategoryRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateSubcategoryCommand implements ICommand {
    private final UUID subcategoryId;
    private final String name;
    private final String description;
    private final UUID categoryId;

    public UpdateSubcategoryCommand(UUID subcategoryId, String name, String description, UUID categoryId) {
        this.subcategoryId = subcategoryId;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    public static UpdateSubcategoryCommand fromRequest(UUID subcategoryId, SubcategoryRequest request) {
        return new UpdateSubcategoryCommand(
                subcategoryId,
                request.getName(),
                request.getDescription(),
                request.getCategoryId()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        // Implementación específica
        return new UpdateSubcategoryMessage();
    }
}

