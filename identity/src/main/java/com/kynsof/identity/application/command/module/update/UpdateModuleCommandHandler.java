package com.kynsof.identity.application.command.module.update;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.identity.domain.rules.module.ModuleNameMustBeUniqueRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerDeleteFileEventService;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateModuleCommandHandler implements ICommandHandler<UpdateModuleCommand> {

    private final IModuleService service;

    @Autowired
    private ProducerSaveFileEventService saveFileEventService;

    @Autowired
    private ProducerDeleteFileEventService deleteFileEventService;

    public UpdateModuleCommandHandler(IModuleService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateModuleCommand command) {

        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Module ID cannot be null."));

        ModuleDto update = this.service.findById(command.getId());

        UpdateIfNotNull.updateIfNotNull(update::setDescription, command.getDescription());

        if (command.getName() != null || !command.getName().isEmpty()) {
            RulesChecker.checkRule(new ModuleNameMustBeUniqueRule(this.service, command.getName(), command.getId()));
            update.setName(command.getName());
        }


        update.setImage(command.getImage());

        this.service.update(update);


    }
}
