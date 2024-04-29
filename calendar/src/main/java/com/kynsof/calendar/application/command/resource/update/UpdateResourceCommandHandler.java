package com.kynsof.calendar.application.command.resource.update;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateResourceCommandHandler implements ICommandHandler<UpdateResourceCommand> {

    private final IResourceService service;
    private final ProducerSaveFileEventService saveFileEventService;

    public UpdateResourceCommandHandler(IResourceService service, ProducerSaveFileEventService saveFileEventService) {
        this.service = service;
        this.saveFileEventService = saveFileEventService;
    }

    @Override
    public void handle(UpdateResourceCommand command) {

        try {
            ResourceDto _resource = service.findById(command.getId());

            _resource.setImage(command.getPicture());
            _resource.setExpressAppointments(command.getExpressAppointments());
            _resource.setStatus(command.getStatus());

            UpdateIfNotNull.updateIfStringNotNull(_resource::setRegistrationNumber, command.getRegistrationNumber());
            UpdateIfNotNull.updateIfStringNotNull(_resource::setLanguage, command.getLanguage());
            UpdateIfNotNull.updateIfStringNotNull(_resource::setIdentification, command.getIdentification());

            service.update(_resource);
        } catch (Exception ex) {

            ResourceDto _resource = new ResourceDto(
                    command.getId(), 
                    "", 
                    command.getRegistrationNumber(), 
                    command.getLanguage(),
                    command.getStatus(), 
                    command.getExpressAppointments(),
                    command.getPicture()
            );
            _resource.setIdentification(command.getIdentification());
            service.create(_resource);
        }

    }

    private UUID saveImagen(byte[] image) {
        if (image != null && image.length > 1) {
            UUID photoId = UUID.randomUUID();
            FileKafka fileSave = new FileKafka(photoId, "calendar", photoId.toString() + ".png", image);
            saveFileEventService.create(fileSave);
            return photoId;
        }
        return null;
    }
}
