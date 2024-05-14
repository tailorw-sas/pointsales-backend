package com.kynsof.calendar.application.command.receipt.createReceiptScheduled;

import com.kynsof.calendar.application.command.receipt.create.CreateReceiptCommand;
import com.kynsof.calendar.domain.dto.*;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.calendar.domain.service.*;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateScheduleReceiptCommandHandler implements ICommandHandler<CreateScheduleReceiptCommand> {

    private final IScheduleService service;
    private final IResourceService serviceResource;
    private final IBusinessService serviceBusiness;
    private final IServiceService serviceService;
    private final IMediator mediator;
    private final IPatientsService patientsService;

    public CreateScheduleReceiptCommandHandler(IScheduleService service, IResourceService serviceResource, IBusinessService serviceBusiness, IServiceService serviceService, IMediator mediator, IPatientsService patientsService) {
        this.service = service;
        this.serviceResource = serviceResource;
        this.serviceBusiness = serviceBusiness;
        this.serviceService = serviceService;
        this.mediator = mediator;
        this.patientsService = patientsService;
    }

    @Override
    public void handle(CreateScheduleReceiptCommand command) {
        ResourceDto _resource = this.serviceResource.findById(command.getResource());
        BusinessDto _business = this.serviceBusiness.findById(command.getBusinessId());
        ServiceDto _service = this.serviceService.findByIds(command.getServiceId());
        PatientDto _patient = this.patientsService.findById(command.getUser());
        UUID id = UUID.randomUUID();
        ScheduleDto _scheduled = new ScheduleDto(id, _resource, _business, command.getDate(), command.getStartTime(), command.getEndingTime(),
                command.getStock(), command.getStock(), EStatusSchedule.ACTIVE,_service);
       UUID scheduledId = service.create(_scheduled);
        command.setId(id);

        CreateReceiptCommand createReceiptCommand = new CreateReceiptCommand(
                _service.getNormalAppointmentPrice(), false, command.getReason(), command.getUser(), scheduledId, command.getServiceId(),
                command.getIpAddress(), command.getUserAgent()
        );
        mediator.send(createReceiptCommand);
    }
}
