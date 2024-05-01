package com.kynsof.calendar.application.command.serviceType.create;

import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.rules.servicetype.SeviceTypeNameMustBeUniqueRule;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

@Component
public class CreateServiceTypeCommandHandler implements ICommandHandler<CreateServiceTypeCommand> {

    private final IServiceTypeService service;
    private final ProducerSaveFileEventService saveFileEventService;

    public CreateServiceTypeCommandHandler(IServiceTypeService service, ProducerSaveFileEventService saveFileEventService) {
        this.service = service;
        this.saveFileEventService = saveFileEventService;
    }

    @Override
    public void handle(CreateServiceTypeCommand command) {
        RulesChecker.checkRule(new SeviceTypeNameMustBeUniqueRule(this.service, command.getName(), command.getId()));

       service.create(new ServiceTypeDto(
                command.getId(),
                command.getName(),
               command.getPicture()
        ));
    }
}
