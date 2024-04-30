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
import org.springframework.util.StringUtils;

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

        ModuleDto module = this.service.findById(command.getId());

        UpdateIfNotNull.updateIfNotNull(module::setDescription, command.getDescription());

        if (!StringUtils.isEmpty(command.getName())) {
            RulesChecker.checkRule(new ModuleNameMustBeUniqueRule(this.service, command.getName(), command.getId()));
            module.setName(command.getName());
        }
        UpdateIfNotNull.updateIfStringNotNull(module::setImage, command.getImage());

        this.service.update(module);


    }
}
