package com.kynsof.calendar.application.command.serviceType.create;

import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.infrastructure.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
        UUID idLogo = null;
        if (command.getPicture() != null && command.getPicture().length > 1) {
            UUID photoId = UUID.randomUUID();
            FileKafka fileSave = new FileKafka(photoId, "calendar", command.getName() + ".png", command.getPicture());
            saveFileEventService.create(fileSave);
            idLogo = photoId;
        }
       service.create(new ServiceTypeDto(
                command.getId(),
                command.getName(),
               idLogo
        ));
    }
}
