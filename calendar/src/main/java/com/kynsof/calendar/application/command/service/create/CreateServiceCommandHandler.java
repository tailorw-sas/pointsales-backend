package com.kynsof.calendar.application.command.service.create;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.calendar.domain.rules.service.SeviceNameMustBeUniqueRule;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

@Component
public class CreateServiceCommandHandler implements ICommandHandler<CreateServiceCommand> {

    private final IServiceService service;
    private final ProducerSaveFileEventService saveFileEventService;
    private final IServiceTypeService serviceTypeService;


    public CreateServiceCommandHandler(IServiceService service, ProducerSaveFileEventService saveFileEventService, IServiceTypeService serviceTypeService) {
        this.service = service;
        this.saveFileEventService = saveFileEventService;
        this.serviceTypeService = serviceTypeService;
    }

    @Override
    public void handle(CreateServiceCommand command) {
        RulesChecker.checkRule(new SeviceNameMustBeUniqueRule(this.service, command.getName(), command.getId()));
        ServiceTypeDto serviceTypeDto = serviceTypeService.findById(command.getType());


        service.create(new ServiceDto(
                command.getId(), 
                serviceTypeDto,
                EServiceStatus.ACTIVE,
                command.getImage(),
                command.getName(), 
                command.getNormalAppointmentPrice(), 
                command.getExpressAppointmentPrice(), 
                command.getDescription(),
                command.isApplyIva() ));
    }
}
