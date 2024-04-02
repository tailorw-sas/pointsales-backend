package com.kynsof.identity.application.command.user.update.steptwo;

import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSystemStepTowCommandHandler implements ICommandHandler<UpdateUserSystemStepTwoCommand> {

    private final IUserSystemService systemService;

    @Autowired
    private ProducerSaveFileEventService saveFileEventService;

    public UpdateUserSystemStepTowCommandHandler(IUserSystemService allergyService) {
        this.systemService = allergyService;
    }

    @Override
    public void handle(UpdateUserSystemStepTwoCommand command) {
        UserSystemDto update = this.systemService.findById(command.getId());
        UUID idImage = UUID.randomUUID();
        update.setIdImage(idImage);
        update.setUserType(command.getUserType());
        systemService.update(update);

        FileKafka fileSave = new FileKafka(idImage, "identity", UUID.randomUUID().toString(),
                command.getImage());
        saveFileEventService.create(fileSave);
    }
}
