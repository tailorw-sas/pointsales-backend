package com.kynsof.identity.application.command.module.update;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.identity.domain.rules.module.ModuleNameMustBeUniqueRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateModuleCommandHandler implements ICommandHandler<UpdateModuleCommand> {

    private final IModuleService service;

    @Autowired
    private ProducerSaveFileEventService saveFileEventService;

    public UpdateModuleCommandHandler(IModuleService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateModuleCommand command) {

        if (command.getName() != null || !command.getName().isEmpty()) {
            RulesChecker.checkRule(new ModuleNameMustBeUniqueRule(this.service, command.getName(), command.getId()));
        }
        UUID idImage = command.getImage() != null ? UUID.randomUUID() : null;
        service.update(new ModuleDto(
                command.getId(),
                command.getName(),
                idImage != null ? idImage : null,
                command.getDescription()
        ));

        if (idImage != null) {
            FileKafka fileSave = new FileKafka(
                    idImage, 
                    "identity", 
                    UUID.randomUUID().toString(),
                    command.getImage()
            );
            saveFileEventService.create(fileSave);
        }
    }
}
