package com.kynsof.treatments.application.command.exam.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateExamCommand implements ICommand {

    private UUID id;
    private String name;
    private String description;
    private String type;
    private String result;
    private Double price;

    public CreateExamCommand(String name, String description, String type, String result, Double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.type = type;
        this.result = result;
        this.price = price;
    }

    public static CreateExamCommand fromRequest(CreateExamRequest request) {
        return new CreateExamCommand(request.getName(), request.getDescription(), request.getType(), request.getResult(), request.getPrice());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateExamMessage(id);
    }
}
