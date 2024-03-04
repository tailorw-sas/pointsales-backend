package com.kynsof.store.application.command.subcategory.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.SubcategoryRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateSubcategoryCommand implements ICommand {
    private String name;
    private String description;
    private UUID categoryId;
    private UUID id;

    public CreateSubcategoryCommand(String name, String description, UUID categoryId) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    public static CreateSubcategoryCommand fromFrontRequest(SubcategoryRequest request) {
        return new CreateSubcategoryCommand(
                request.getName(),
                request.getDescription(),
                request.getCategoryId()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateSubcategoryMessage(id);
    }
}

