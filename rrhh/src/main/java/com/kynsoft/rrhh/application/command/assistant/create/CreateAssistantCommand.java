package com.kynsoft.rrhh.application.command.assistant.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateAssistantCommand implements ICommand {

    private UUID id;
    private String identification;
    private String email;
    private String name;
    private String lastName;
    private String status;
    private String phoneNumber;
    private String image;
    private String department;

    public CreateAssistantCommand(String identification, String email, String name, String lastName, String status,
                                  String phoneNumber, String image, String department) {
        this.id = UUID.randomUUID();
        this.identification = identification;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.department = department;
    }

    public static CreateAssistantCommand fromRequest(CreateAssistantRequest request) {
        return new CreateAssistantCommand(
                request.getIdentification(),
                request.getEmail(),
                request.getName(),
                request.getLastName(),
                request.getStatus(),
                request.getPhoneNumber(),
                request.getImage(),
                request.getDepartment()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAssistantMessage(id);
    }
}