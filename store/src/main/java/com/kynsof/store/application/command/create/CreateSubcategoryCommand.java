package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateSubcategoryCommand implements ICommand {
    private String name;
    private String description;
    private UUID categoryId;

    public CreateSubcategoryCommand(String name, String description, UUID categoryId) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    @Override
    public ICommandMessage getMessage() {
        // Implementar lógica para obtener el mensaje de comando correspondiente
        return null;
    }
}

