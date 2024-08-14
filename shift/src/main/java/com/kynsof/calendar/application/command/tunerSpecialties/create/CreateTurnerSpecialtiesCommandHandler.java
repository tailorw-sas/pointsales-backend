package com.kynsof.calendar.application.command.tunerSpecialties.create;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.TurnerSpecialtiesDto;
import com.kynsof.calendar.domain.dto.enumType.ETurnerSpecialtiesStatus;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.response.ErrorField;
import java.util.UUID;
import org.springframework.stereotype.Component;
import com.kynsof.calendar.domain.service.ITurnerSpecialtiesService;

@Component
public class CreateTurnerSpecialtiesCommandHandler implements ICommandHandler<CreateTurnerSpecialtiesCommand> {

    private final ITurnerSpecialtiesService turnerSpecialtiesService;
    private final IResourceService resourceService;
    private final IServiceService serviceService;

    public CreateTurnerSpecialtiesCommandHandler(ITurnerSpecialtiesService turnerSpecialtiesService,
                                                 IResourceService resourceService,
                                                 IServiceService serviceService) {

        this.turnerSpecialtiesService = turnerSpecialtiesService;
        this.resourceService = resourceService;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(CreateTurnerSpecialtiesCommand command) {

        ResourceDto doctor = this.resourceService.findById(this.validateUUID(command.getResource()));
        ServiceDto service = this.serviceService.findByIds(this.validateUUID(command.getService()));

        this.turnerSpecialtiesService.create(new TurnerSpecialtiesDto(
                command.getId(), 
                command.getMedicalHistory(), 
                command.getPatient(), 
                command.getIdentification(), 
                doctor, 
                service, 
                this.validateStatus(command.getStatus()), 
                null, 
                null
        ));
    }

    private UUID validateUUID(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.UUID_NOT_FORMAT, new ErrorField("id", DomainErrorMessage.UUID_NOT_FORMAT.getReasonPhrase())));
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