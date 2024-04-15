package com.kynsof.calendar.application.command.service.update;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateServiceCommandHandler  implements ICommandHandler<UpdateServiceCommand> {

    private final IServiceService service;
    private final IServiceTypeService serviceTypeService;
    private final ProducerSaveFileEventService saveFileEventService;

    public UpdateServiceCommandHandler(IServiceService service, IServiceTypeService serviceTypeService, ProducerSaveFileEventService saveFileEventService) {
        this.service = service;
        this.serviceTypeService = serviceTypeService;
        this.saveFileEventService = saveFileEventService;
    }

    @Override
    public void handle(UpdateServiceCommand command) {
        ServiceTypeDto serviceTypeDto = serviceTypeService.findById(command.getServiceTypeId());

        String idLogo = "";
        if (command.getPicture() != null && command.getPicture().length > 1) {
            UUID photoId = UUID.randomUUID();
            FileKafka fileSave = new FileKafka(photoId, "calendar", command.getName() + ".png", command.getPicture());
            saveFileEventService.create(fileSave);
            idLogo = photoId.toString();
        }
       service.update(new ServiceDto(
               command.getId(), 
               serviceTypeDto,
               EServiceStatus.ACTIVE,
               idLogo,
               command.getName(), 
               command.getNormalAppointmentPrice(), 
               command.getExpressAppointmentPrice(), 
               command.getDescription(),
               command.isApplyIva()
       ));
    }
}