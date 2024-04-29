package com.kynsof.calendar.application.command.service.update;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

@Component
public class UpdateServiceCommandHandler implements ICommandHandler<UpdateServiceCommand> {

    private final IServiceService service;
    private final IServiceTypeService serviceTypeService;
    private final ProducerSaveFileEventService saveFileEventService;

    public UpdateServiceCommandHandler(IServiceService service, IServiceTypeService serviceTypeService, ProducerSaveFileEventService saveFileEventService) {
        this.service = service;
        this.serviceTypeService = serviceTypeService;
        this.saveFileEventService = saveFileEventService;
    }

    @Override
    public void handle(UpdateServiceCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getServiceTypeId(), "id", "Service Type ID cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Service ID cannot be null."));

        ServiceTypeDto serviceTypeDto = serviceTypeService.findById(command.getServiceTypeId());
        ServiceDto update = service.findById(command.getId());
        update.setType(serviceTypeDto);
        update.setPicture(command.getPicture());

        update.setApplyIva(command.isApplyIva());
        update.setExpressAppointmentPrice(command.getExpressAppointmentPrice());
        update.setNormalAppointmentPrice(command.getNormalAppointmentPrice());

        UpdateIfNotNull.updateIfStringNotNull(update::setName, command.getName());
        UpdateIfNotNull.updateIfStringNotNull(update::setDescription, command.getDescription());
        service.update(update);
    }
}
