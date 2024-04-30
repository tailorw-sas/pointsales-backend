package com.kynsof.identity.application.command.user.update;

import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.service.IUserSystemService;
import com.kynsof.identity.infrastructure.services.kafka.producer.ProducerResourceEventService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.UserType;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSystemCommandHandler implements ICommandHandler<UpdateUserSystemCommand> {

    private final IUserSystemService systemService;

    @Autowired
    private ProducerSaveFileEventService saveFileEventService;

    @Autowired
    private ProducerResourceEventService resourceEventService;

    public UpdateUserSystemCommandHandler(IUserSystemService systemService) {
        this.systemService = systemService;
    }

    @Override
    public void handle(UpdateUserSystemCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "UserSystem ID cannot be null."));
        UserSystemDto objectToUpdate = this.systemService.findById(command.getId());

        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setEmail, command.getEmail());
        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setLastName, command.getLastName());
        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setName, command.getName());
        objectToUpdate.setUserType(command.getUserType());
        objectToUpdate.setImage(command.getImage());


        systemService.update(objectToUpdate);
        if (command.getUserType().equals(UserType.DOCTORS) ||
                command.getUserType().equals(UserType.ASSISTANTS) ||
                command.getUserType().equals(UserType.NURSES)) {
            resourceEventService.create(objectToUpdate);
        }
    }
}
