package com.kynsof.patients.application.command.patients.createInsurance;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateInsuranceCommand implements ICommand {
    private boolean result;
    private List<UUID> insuranceIds;
    private UUID patientId;


    public CreateInsuranceCommand(UUID patientId, List<UUID> insuranceIds) {
        this.result = false;
        this.insuranceIds = insuranceIds;
        this.patientId = patientId;
    }

    public static CreateInsuranceCommand fromRequest(CreateInsuranceRequest request) {
        return new CreateInsuranceCommand(request.getPatientId(), request.getInsuranceIds());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateInsuranceMessage(result);
    }
}
