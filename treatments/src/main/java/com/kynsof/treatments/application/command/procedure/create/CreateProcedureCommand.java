package com.kynsof.treatments.application.command.procedure.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateProcedureCommand implements ICommand {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private MedicalExamCategory type;

    public CreateProcedureCommand(String code, String name, String description, MedicalExamCategory type) {
        this.id = UUID.randomUUID();
        this.code = code;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public static CreateProcedureCommand fromRequest(CreateProcedureRequest request) {
        return new CreateProcedureCommand(request.getCode(), request.getName(), request.getDescription(), request.getType());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateProcedureMessage(id);
    }
}
