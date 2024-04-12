package com.kynsof.treatments.application.command.vaccine.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateVaccineCommandHandler implements ICommandHandler<CreateVaccineCommand> {

    private final IVaccineService serviceImpl;

    public CreateVaccineCommandHandler(IVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateVaccineCommand command) {
       UUID id = serviceImpl.create(new VaccineDto(
                UUID.randomUUID(),
               command.getName(), command.getDescription(), command.getType(),
               command.getMinAge(), command.getMaxAge(),command.getDose(), command.getRouteOfAdministration(),
               command.getPreventableDiseases(),
               command.getServiceId()
        ));
       command.setId(id);
    }
}
