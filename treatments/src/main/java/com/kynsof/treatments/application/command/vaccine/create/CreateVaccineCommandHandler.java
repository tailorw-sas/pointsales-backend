package com.kynsof.treatments.application.command.vaccine.create;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.rules.vaccine.VaccineNameMustBeUniqueRule;
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

        //TODO yannier completar las reglas de negocio
        UUID id = UUID.randomUUID();
        RulesChecker.checkRule(new VaccineNameMustBeUniqueRule(this.serviceImpl, command.getName(), id));
        serviceImpl.create(new VaccineDto(
                id,
                command.getName(), command.getDescription(), command.getType(),
                command.getMinAge(), command.getMaxAge(), command.getDose(), command.getRouteOfAdministration(),
                command.getPreventableDiseases(),
                command.getServiceId()
        ));
        command.setId(id);
    }
}
