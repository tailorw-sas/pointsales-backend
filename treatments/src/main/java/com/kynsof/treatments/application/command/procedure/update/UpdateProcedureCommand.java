package com.kynsof.treatments.application.command.procedure.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateProcedureCommand implements ICommand {

    private UUID id;
    private String code;
    private String name;
    private String description;
    private MedicalExamCategory type;
    private Double price;

    public UpdateProcedureCommand(UUID id, String code, String name, String description, MedicalExamCategory type, Double price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public static UpdateProcedureCommand fromRequest(UpdateProcedureRequest request, UUID id) {
        return new UpdateProcedureCommand(id, request.getCode(), request.getName(), request.getDescription(), request.getType(), request.getPrice());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateProcedureMessage(id);
    }
}
