package com.kynsof.store.application.command.category.command.update;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.CategoryRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateCategoryCommand implements ICommand {
    private final UUID categoryId;
    private final String name;
    private final String description;

    public UpdateCategoryCommand(UUID categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public static UpdateCategoryCommand fromRequest(UUID categoryId, CategoryRequest request) {
        return new UpdateCategoryCommand(
                categoryId,
                request.getName(),
                request.getDescription()
        );
    }


    @Override
    public ICommandMessage getMessage() {
        // Implementación específica
        return new UpdateCategoryMessage();
    }
}
