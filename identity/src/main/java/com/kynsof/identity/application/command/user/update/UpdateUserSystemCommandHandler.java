package com.kynsof.identity.application.command.user.update;

import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.identity.infrastructure.services.kafka.producer.ProducerUpdateUserSystemEventService;
import com.kynsof.share.core.domain.RulesChecker;
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
    private ProducerUpdateUserSystemEventService updateUserSystemEventService;

    public UpdateUserSystemCommandHandler(IUserSystemService allergyService) {
        this.systemService = allergyService;
    }

    @Override
    public void handle(UpdateUserSystemCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule(command.getId(), "UserSystem.id", "UserSystem ID cannot be null."));
        UserSystemDto objectToUpdate = this.systemService.findById(command.getId());

        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setEmail, command.getEmail());
        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setLastName, command.getLastName());
        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setName, command.getName());
        systemService.update(objectToUpdate);
        updateUserSystemEventService.update(objectToUpdate);
    }
}
