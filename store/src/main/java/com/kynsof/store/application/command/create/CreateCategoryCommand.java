package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryCommand implements ICommand {
    private String name;
    private String description;

    public CreateCategoryCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public ICommandMessage getMessage() {
        // Implementar lógica para obtener el mensaje de comando correspondiente
        return null;
    }
}
