package com.kynsof.treatments.application.command.vaccine.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

@Component
public class UpdateVaccineCommandHandler implements ICommandHandler<UpdateVaccineCommand> {

    private final IPatientsService serviceImpl;

    public UpdateVaccineCommandHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateVaccineCommand command) {
        serviceImpl.update(new PatientDto(
                command.getId(),
                command.getIdentification(),
                command.getName(),
                command.getLastName(),
                command.getGender(),
                Status.ACTIVE,
                command.getBirthDate()
        ));

    }
}
