package com.kynsof.calendar.application.command.turn.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateTurnCommand implements ICommand {

    private UUID id;
    private UUID doctor;
    private UUID service;
    private String identification;
    private Boolean isPreferential;
    private final UUID business;

    public CreateTurnCommand(UUID doctor, UUID service, String identification,
                             Boolean isPreferential,
                             UUID business) {
        this.id = UUID.randomUUID();
        this.doctor = doctor;
        this.service = service;
        this.identification = identification;
        this.isPreferential = isPreferential;
        this.business = business;
    }

    public static CreateTurnCommand fromRequest(CreateTurnRequest request) {
        return new CreateTurnCommand(request.getDoctor(), request.getService(), request.getIdentification(),
                 request.getIsPreferential(),
                request.getBusiness());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateTurnMessage(id);
    }
}