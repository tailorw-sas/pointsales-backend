package com.kynsof.calendar.application.command.service.create;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.rules.service.ServiceCodeMustBeUniqueRule;
import com.kynsof.calendar.domain.rules.service.SeviceNameMustBeUniqueRule;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.calendar.infrastructure.service.kafka.producer.service.ProducerServiceEventService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.Servicekafka;
import org.springframework.stereotype.Component;

@Component
public class CreateServiceCommandHandler implements ICommandHandler<CreateServiceCommand> {

    private final IServiceService service;
    private final IServiceTypeService serviceTypeService;
    private final ProducerServiceEventService producerServiceEventService;

    public CreateServiceCommandHandler(IServiceService service, 
                                       IServiceTypeService serviceTypeService,
                                       ProducerServiceEventService producerServiceEventService) {
        this.service = service;
        this.serviceTypeService = serviceTypeService;
        this.producerServiceEventService = producerServiceEventService;
    }

    @Override
    public void handle(CreateServiceCommand command) {
        RulesChecker.checkRule(new SeviceNameMustBeUniqueRule(this.service, command.getName(), command.getId()));
        RulesChecker.checkRule(new ServiceCodeMustBeUniqueRule(this.service, command.getCode(), command.getId()));
        ServiceTypeDto serviceTypeDto = serviceTypeService.findById(command.getType());

        ServiceDto serviceDto = service.create(new ServiceDto(
                command.getId(),
                serviceTypeDto,
                command.getStatus(),
                command.getImage(),
                command.getName(),
                command.getNormalAppointmentPrice(),
                command.getDescription(),
                command.isApplyIva(),
                command.getEstimatedDuration(),
                command.getCode()
        ));

        command.setId(serviceDto.getId());
        this.producerServiceEventService.create(new Servicekafka(
                serviceDto.getId(), 
                serviceDto.getType().getId(), 
                serviceDto.getStatus().name(), 
                serviceDto.getPicture(), 
                serviceDto.getName(), 
                serviceDto.getNormalAppointmentPrice(),
                serviceDto.getDescription(), 
                serviceDto.getApplyIva(), 
                serviceDto.getEstimatedDuration(), 
                serviceDto.getCode()
        ));
    }
}
