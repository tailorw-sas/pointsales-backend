package com.kynsof.identity.application.command.module.update;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.identity.domain.rules.module.ModuleNameMustBeUniqueRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerDeleteFileEventService;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import java.util.UUID;
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

        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "Module.id", "Module ID cannot be null."));

        ModuleDto update = this.service.findById(command.getId());

        UpdateIfNotNull.updateIfNotNull(update::setDescription, command.getDescription());

        if (command.getName() != null || !command.getName().isEmpty()) {
            RulesChecker.checkRule(new ModuleNameMustBeUniqueRule(this.service, command.getName(), command.getId()));
            update.setName(command.getName());
        }

        //Guardo el id del logo actual, para si cambia, mandar a elimianrlo al S3.
        String idImageDelete = update.getImage().toString();
        UUID idImageSave = command.getImage() != null ? UUID.randomUUID() : null;
        update.setImage(idImageSave != null ? idImageSave : update.getImage());

        this.service.update(update);

        if (idImageSave != null) {
            //Si logoId es diferente de null, fue porque se cambio, por lo cual debe ser eliminado en actual.
            this.deleteFileEventService.delete(new FileKafka(UUID.fromString(idImageDelete), "identity", "", null));
            this.saveFileEventService.create(new FileKafka(idImageSave, "identity", UUID.randomUUID().toString(),command.getImage()));
        }
    }
}
