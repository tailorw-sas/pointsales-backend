package com.kynsof.calendar.application.command.resource.update;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
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
        UUID idLogo = null;
        if (command.getPicture() != null && command.getPicture().length > 1) {
            UUID photoId = UUID.randomUUID();
            FileKafka fileSave = new FileKafka(photoId, "calendar", command.getRegistrationNumber() + ".png", command.getPicture());
            saveFileEventService.create(fileSave);
            idLogo = photoId;
        }

        //TODO yannier las reglas de validacion
        try {
            ResourceDto _resource = service.findById(command.getId());

            _resource.setRegistrationNumber(command.getRegistrationNumber());
            _resource.setLanguage(command.getLanguage());
            _resource.setExpressAppointments(command.getExpressAppointments());
            _resource.setImage(idLogo);
            _resource.setIdentification(command.getIdentification());
            service.update(_resource);
        }catch (Exception ex){
            ResourceDto _resource = new ResourceDto(command.getId(),"", command.getRegistrationNumber(), command.getLanguage(),
                    command.getStatus(),command.getExpressAppointments(), idLogo);
            _resource.setRegistrationNumber(command.getRegistrationNumber());
            _resource.setImage(idLogo);
            _resource.setLanguage(command.getLanguage());
            _resource.setExpressAppointments(command.getExpressAppointments());
            _resource.setIdentification(command.getIdentification());
            service.create(_resource);
        }

    }
}
