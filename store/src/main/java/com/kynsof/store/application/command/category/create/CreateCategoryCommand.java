package com.kynsof.store.application.command.category.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.category.CategoryRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateCategoryCommand implements ICommand {
    private String name;
    private String description;
    private UUID id;

    public CreateCategoryCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public static CreateCategoryCommand fromRequest(CategoryRequest request) {
        return new CreateCategoryCommand(
                request.getName(),
                request.getDescription()
        );
    }

    @Override
    public ICommandMessage getMessage() {

        return new CreateCategoryMessage(id);
    }
}
