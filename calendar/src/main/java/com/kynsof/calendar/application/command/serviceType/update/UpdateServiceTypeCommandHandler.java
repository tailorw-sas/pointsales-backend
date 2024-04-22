package com.kynsof.calendar.application.command.serviceType.update;

import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateServiceTypeCommandHandler implements ICommandHandler<UpdateServiceTypeCommand> {

    private final IServiceTypeService service;
    private final ProducerSaveFileEventService saveFileEventService;

    public UpdateServiceTypeCommandHandler(IServiceTypeService service, ProducerSaveFileEventService saveFileEventService) {
        this.service = service;
        this.saveFileEventService = saveFileEventService;
    }

    @Override
    public void handle(UpdateServiceTypeCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Service Type ID cannot be null."));

        ServiceTypeDto update = this.service.findById(command.getId());

        if (command.getPicture() != null && command.getPicture().length > 1) {
            UUID photoId = UUID.randomUUID();
            FileKafka fileSave = new FileKafka(photoId, "calendar", command.getName() + ".png", command.getPicture());
            saveFileEventService.create(fileSave);
            update.setPicture(photoId);
        }

        UpdateIfNotNull.updateIfStringNotNull(update::setName, command.getName());
        service.update(update);
    }
}
