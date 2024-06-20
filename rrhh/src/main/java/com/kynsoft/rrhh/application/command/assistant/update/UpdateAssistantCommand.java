package com.kynsoft.rrhh.application.command.assistant.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateAssistantCommand implements ICommand {

    private UUID id;
    private final String identification;
    private final String email;
    private final String name;
    private final String lastName;
    private final String status;
    private final String phoneNumber;
    private final String image;
    private final String department;


    public UpdateAssistantCommand(UUID id, String identification, String email, String name, String lastName,
                                  String status, String phoneNumber, String image, String department) {
        this.id = id;

        this.identification = identification;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.department = department;
    }

    public static UpdateAssistantCommand fromRequest(UpdateAssistantRequest request, UUID id) {
        return new UpdateAssistantCommand(
                id,
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
        return new UpdateAssistantMessage(id);
    }
}
