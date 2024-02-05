package com.kynsof.patients.application.command.insurance.create;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateInsuranceMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "CREATE_PATIENT_INSURANCE";

    public CreateInsuranceMessage(boolean result) {
        this.result = result;
    }

}
