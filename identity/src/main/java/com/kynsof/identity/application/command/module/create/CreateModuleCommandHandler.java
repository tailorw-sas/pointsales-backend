package com.kynsof.identity.application.command.module.create;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.identity.domain.rules.module.ModuleDescriptionMustBeNullRule;
import com.kynsof.identity.domain.rules.module.ModuleNameMustBeNullRule;
import com.kynsof.identity.domain.rules.module.ModuleNameMustBeUniqueRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
        RulesChecker.checkRule(new ModuleNameMustBeNullRule(command.getName()));
        RulesChecker.checkRule(new ModuleDescriptionMustBeNullRule(command.getDescription()));
        RulesChecker.checkRule(new ModuleNameMustBeUniqueRule(this.service, command.getName(), command.getId()));

        UUID idImage = UUID.randomUUID();
        service.create(new ModuleDto(
                command.getId(),
                command.getName(),
                command.getImage(),
                command.getDescription())
        );
//        FileKafka fileSave = new FileKafka(idImage, "identity", UUID.randomUUID().toString(),
//                command.getImage());
      //  saveFileEventService.create(fileSave);
    }
}
