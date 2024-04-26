package com.kynsof.treatments.application.command.medicine.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateMedicineCommand implements ICommand {

    private UUID id;
    private String name;

    public CreateMedicineCommand(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public static CreateMedicineCommand fromRequest(CreateMedicineRequest request) {
        return new CreateMedicineCommand(
                request.getName()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateMedicineMessage(id);
    }
}
