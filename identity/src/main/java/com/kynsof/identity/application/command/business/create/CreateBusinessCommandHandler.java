package com.kynsof.identity.application.command.business.create;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.dto.GeographicLocationDto;
import com.kynsof.identity.domain.dto.enumType.EBusinessStatus;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.identity.domain.interfaces.service.IGeographicLocationService;
import com.kynsof.identity.domain.rules.business.BusinessNameMustBeUniqueRule;
import com.kynsof.identity.domain.rules.business.BusinessRucCheckingNumberOfCharactersRule;
import com.kynsof.identity.domain.rules.business.BusinessRucMustBeUniqueRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateBusinessCommandHandler implements ICommandHandler<CreateBusinessCommand> {

    private final IBusinessService service;

    @Autowired
    private ProducerSaveFileEventService saveFileEventService;

    @Autowired
    private IGeographicLocationService geographicLocationService;

    public CreateBusinessCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateBusinessCommand command) {

        RulesChecker.checkRule(new BusinessRucCheckingNumberOfCharactersRule(command.getRuc()));
        RulesChecker.checkRule(new BusinessRucMustBeUniqueRule(this.service, command.getRuc(), command.getId()));
        RulesChecker.checkRule(new BusinessNameMustBeUniqueRule(this.service, command.getName(), command.getId()));

        GeographicLocationDto location = this.geographicLocationService.findById(command.getGeographicLocation());
        UUID idLogo = UUID.randomUUID();
        UUID id = service.create(new BusinessDto(
                command.getId(),
                command.getName(),
                command.getLatitude(),
                command.getLongitude(),
                command.getDescription(),
                idLogo.toString(),
                command.getRuc(),
                EBusinessStatus.ACTIVE,
                location,
                command.getAddress()
        ));
        if (command.getLogo() != null) {
            FileKafka fileSave = new FileKafka(idLogo, "identity", UUID.randomUUID().toString(),
                    command.getLogo());
            saveFileEventService.create(fileSave);
        }
        command.setId(id);

    }
}