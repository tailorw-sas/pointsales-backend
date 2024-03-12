package com.kynsof.calendar.application.command.business.create;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.share.core.infrastructure.ProducerSaveFileEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateBusinessCommandHandler implements ICommandHandler<CreateBusinessCommand> {

    private final IBusinessService service;

    @Autowired
    private ProducerSaveFileEventService saveFileEventService;

    public CreateBusinessCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateBusinessCommand command) {

        UUID idLogo = UUID.randomUUID();
        service.create(new BusinessDto(
                command.getId(),
                command.getName(),
                command.getLatitude(),
                command.getLongitude(),
                command.getDescription(),
                command.getLogo().getFile(),
                idLogo,
                command.getRuc()
        ));
        FileKafka fileSave = new FileKafka(idLogo, "calendar", command.getLogo().getFileName(), command.getLogo().getFile());
        saveFileEventService.create(fileSave);
    }
}