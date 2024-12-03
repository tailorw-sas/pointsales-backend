package com.kynsof.treatments.application.command.exam.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateExamCommand implements ICommand {

    private UUID id;
    private String name;
    private String description;
    private MedicalExamCategory type;
    private String result;

    private String code;

    public UpdateExamCommand(UUID id, String name, String description, MedicalExamCategory type, String result, String code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.result = result;
        this.code = code;
    }

    public static UpdateExamCommand fromRequest(UpdateExamRequest request, UUID id) {
        return new UpdateExamCommand(
                id, 
                request.getName(), 
                request.getDescription(), 
                request.getType(), 
                request.getResult(),
                request.getCode()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateExamMessage(id);
    }
}
