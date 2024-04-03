package com.kynsof.identity.application.command.user.update;

import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSystemCommandHandler implements ICommandHandler<UpdateUserSystemCommand> {

    private final IUserSystemService systemService;

    @Autowired
    private ProducerSaveFileEventService saveFileEventService;

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
        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setStatus, command.getStatus());
        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setUserType, command.getUserType());

        /**
         * Valida si la imagen viene diferente de null, si es null no se cambia.
         */
        UUID idImage = command.getImage() != null ? UUID.randomUUID() : null;
        if (idImage != null) {
            objectToUpdate.setIdImage(idImage);
            FileKafka fileSave = new FileKafka(idImage, "identity", UUID.randomUUID().toString(),
                    command.getImage());
            saveFileEventService.create(fileSave);
        }

        systemService.update(objectToUpdate);
    }
}
