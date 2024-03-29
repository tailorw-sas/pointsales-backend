package com.kynsof.identity.application.command.business.update;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.dto.GeographicLocationDto;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.identity.domain.interfaces.service.IGeographicLocationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UpdateBusinessCommandHandler implements ICommandHandler<UpdateBusinessCommand> {

    private final IBusinessService service;

    @Autowired
    private ProducerSaveFileEventService saveFileEventService;

    @Autowired
    private IGeographicLocationService geographicLocationService;

    public UpdateBusinessCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateBusinessCommand command) {
        GeographicLocationDto location = this.geographicLocationService.findById(command.getGeographicLocation());
        /**
         * Verifica que logoId venga en null, si esta en null, es porque no se
         * cambio.
         */
        UUID logoId = command.getLogo() != null ? UUID.randomUUID() : null;
        service.update(new BusinessDto(
                command.getId(),
                command.getName(),
                command.getLatitude(),
                command.getLongitude(),
                command.getDescription(),
                logoId != null ? logoId.toString() : null,
                command.getRuc(),
                command.getStatus(),
                location
        ));
        if (logoId != null) {
            FileKafka fileSave = new FileKafka(logoId, "identity", command.getLogo().getFileName(),
                command.getLogo().getFile());
            saveFileEventService.create(fileSave);
        }
    }
}
