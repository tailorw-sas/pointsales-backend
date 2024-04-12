package com.kynsof.treatments.application.command.vaccine.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

@Component
public class UpdateVaccineCommandHandler implements ICommandHandler<UpdateVaccineCommand> {

    private final IVaccineService serviceImpl;

    public UpdateVaccineCommandHandler(IVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateVaccineCommand command) {
        VaccineDto _vaccine = this.serviceImpl.findById(command.getId());

        //TODO Yannier realizar las reglas de validacion

        _vaccine.setName(command.getName());
        _vaccine.setDescription(command.getDescription());
        _vaccine.setType(command.getType());
        _vaccine.setMinAge(command.getMinAge());
        _vaccine.setMaxAge(command.getMaxAge());
        _vaccine.setDose(command.getDose());
        _vaccine.setRouteOfAdministration(command.getRouteOfAdministration());
        _vaccine.setPreventableDiseases(command.getPreventableDiseases());
        _vaccine.setServiceId(command.getServiceId());
        serviceImpl.update(_vaccine);

    }
}
