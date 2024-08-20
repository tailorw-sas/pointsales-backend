package com.kynsof.calendar.application.command.tunerSpecialties.update;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.TurnerSpecialtiesDto;
import com.kynsof.calendar.domain.dto.enumType.ETurnerSpecialtiesStatus;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.ITurnerSpecialtiesService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.response.ErrorField;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateTurnerSpecialtiesCommandHandler implements ICommandHandler<UpdateTurnerSpecialtiesCommand> {

    private final ITurnerSpecialtiesService turnerSpecialtiesService;
    private final IResourceService resourceService;
    private final IServiceService serviceService;

    public UpdateTurnerSpecialtiesCommandHandler(ITurnerSpecialtiesService turnerSpecialtiesService,
                                                 IResourceService resourceService,
                                                 IServiceService serviceService) {

        this.turnerSpecialtiesService = turnerSpecialtiesService;
        this.resourceService = resourceService;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(UpdateTurnerSpecialtiesCommand command) {

        TurnerSpecialtiesDto turnerSpecialtiesDto = this.turnerSpecialtiesService.findById(this.validateUUID(command.getId()));
        ResourceDto doctor = this.resourceService.findById(this.validateUUID(command.getResource()));
        ServiceDto service = this.serviceService.findByIds(this.validateUUID(command.getService()));
        turnerSpecialtiesDto.setIdentification(command.getIdentification());
        turnerSpecialtiesDto.setMedicalHistory(command.getMedicalHistory());
        turnerSpecialtiesDto.setPatient(command.getPatient());
        turnerSpecialtiesDto.setResource(doctor);
        turnerSpecialtiesDto.setService(service);
        turnerSpecialtiesDto.setStatus(this.validateStatus(command.getStatus()));
        this.turnerSpecialtiesService.create(turnerSpecialtiesDto);
    }

    private UUID validateUUID(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(
                    DomainErrorMessage.UUID_NOT_FORMAT, new ErrorField("id", DomainErrorMessage.UUID_NOT_FORMAT.getReasonPhrase())));
        }
    }

    private ETurnerSpecialtiesStatus validateStatus(String status) {
        try {
            return ETurnerSpecialtiesStatus.valueOf(status);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.STATUS_NOT_FORMAT, new ErrorField("status", DomainErrorMessage.STATUS_NOT_FORMAT.getReasonPhrase())));
        }
    }

}