package com.kynsof.calendar.application.command.service.create;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateServiceCommandHandler implements ICommandHandler<CreateServiceCommand> {

    private final IServiceService service;
    private final ProducerSaveFileEventService saveFileEventService;
    private final IServiceTypeService serviceTypeService;


    public CreateServiceCommandHandler(IServiceService service, ProducerSaveFileEventService saveFileEventService, IServiceTypeService serviceTypeService) {
        this.service = service;
        this.saveFileEventService = saveFileEventService;
        this.serviceTypeService = serviceTypeService;
    }

    @Override
    public void handle(CreateServiceCommand command) {
        ServiceTypeDto serviceTypeDto = serviceTypeService.findById(command.getServiceTypeId());
        String idLogo = "";
        if (command.getPicture() != null && command.getPicture().length > 1) {
            UUID photoId = UUID.randomUUID();
            FileKafka fileSave = new FileKafka(photoId, "calendar", command.getName() + ".png", command.getPicture());
            saveFileEventService.create(fileSave);
            idLogo = photoId.toString();
        }

        service.create(new ServiceDto(
                command.getId(), 
                serviceTypeDto,
                idLogo,
                command.getName(), 
                command.getNormalAppointmentPrice(), 
                command.getExpressAppointmentPrice(), 
                command.getDescription()));
    }
}
