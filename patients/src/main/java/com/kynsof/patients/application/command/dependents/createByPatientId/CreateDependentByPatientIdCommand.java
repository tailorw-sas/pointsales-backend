package com.kynsof.patients.application.command.dependents.createByPatientId;

import com.kynsof.patients.domain.dto.enumTye.FamilyRelationship;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateDependentByPatientIdCommand implements ICommand {
    private UUID id;
    private UUID primeId;
    private UUID patientId;
    private FamilyRelationship familyRelationship;

    public CreateDependentByPatientIdCommand(UUID primeId, UUID patientId, FamilyRelationship familyRelationship ){
        this.primeId = primeId;
        this.patientId = patientId;
        this.familyRelationship = familyRelationship;

    }

    public static CreateDependentByPatientIdCommand fromRequest(CreateDependentByPatientIdRequest request) {
        return new CreateDependentByPatientIdCommand(request.getPrimeId(),request.getPatientId(), request.getFamilyRelationship()
                );
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateDependentByPatientIdMessage(id);
    }
}
