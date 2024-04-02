package com.kynsof.identity.application.command.module.create;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateModuleCommandHandler implements ICommandHandler<CreateModuleCommand> {

    private final IModuleService service;

    @Autowired
    private ProducerSaveFileEventService saveFileEventService;

    public CreateModuleCommandHandler(IModuleService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateModuleCommand command) {

        UUID idImage = UUID.randomUUID();
        service.create(new ModuleDto(
                command.getId(),
                command.getName(),
                idImage,
                command.getDescription())
        );
        FileKafka fileSave = new FileKafka(idImage, "identity", UUID.randomUUID().toString(),
                command.getImage());
        saveFileEventService.create(fileSave);
    }
}
