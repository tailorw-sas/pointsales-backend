package com.kynsof.patients.application.command.insurance.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateNewInsuranceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_INSURANCE";
    public CreateNewInsuranceMessage(UUID id) {
        this.id = id;
    }

}
