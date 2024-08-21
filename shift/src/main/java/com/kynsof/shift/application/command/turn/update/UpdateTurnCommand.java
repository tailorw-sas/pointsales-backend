package com.kynsof.shift.application.command.turn.update;

import com.kynsof.shift.domain.dto.enumType.EPriority;
import com.kynsof.shift.domain.dto.enumType.ETurnStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateTurnCommand implements ICommand {

    private UUID id;
    private UUID doctor;
    private UUID service;
    private String identification;
    private EPriority priority;
    private Boolean isPreferential;
    private final UUID business;
    private final ETurnStatus status;

    public UpdateTurnCommand(UUID id,UUID doctor, UUID service, String identification,
                             EPriority priority, Boolean isPreferential,
                             UUID business, ETurnStatus status) {
        this.status = status;
        this.id = id;
        this.doctor = doctor;
        this.service = service;
        this.identification = identification;
        this.priority = priority;
        this.isPreferential = isPreferential;
        this.business = business;
    }

    public static UpdateTurnCommand fromRequest(UUID id,UpdateTurnRequest request) {
        return new UpdateTurnCommand(id,request.getDoctor(), request.getService(), request.getIdentification(),
                request.getPriority(), request.getIsPreferential(),
                request.getBusiness(), request.getStatus());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateTurnMessage(id);
    }
}
