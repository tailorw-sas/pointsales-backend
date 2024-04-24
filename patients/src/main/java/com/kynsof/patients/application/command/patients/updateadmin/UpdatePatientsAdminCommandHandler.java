package com.kynsof.patients.application.command.patients.updateadmin;

import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerUpdatePatientsEventService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdatePatientsAdminCommandHandler implements ICommandHandler<UpdatePatientAdminCommand> {

    private final IPatientsService serviceImpl;
    private final ProducerSaveFileEventService saveFileEventService;

    private final ProducerUpdatePatientsEventService updatePatientsEventService;

    public UpdatePatientsAdminCommandHandler(IPatientsService serviceImpl,
                                        ProducerSaveFileEventService saveFileEventService, ProducerUpdatePatientsEventService updatePatientsEventService
    ) {
        this.serviceImpl = serviceImpl;
        this.saveFileEventService = saveFileEventService;
        this.updatePatientsEventService = updatePatientsEventService;
    }

    @Override
    public void handle(UpdatePatientAdminCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Patients ID cannot be null."));
        PatientDto update = serviceImpl.findByIdSimple(command.getId());

        if (command.getPhoto() != null && command.getPhoto().length > 1) {
            UUID photoId = UUID.randomUUID();
            FileKafka fileSave = new FileKafka(photoId, "patients", update.getName() + ".png", command.getPhoto());
            saveFileEventService.create(fileSave);
            update.setPhoto(photoId.toString());
        }

        UpdateIfNotNull.updateIfStringNotNull(update::setIdentification, command.getIdentification());

        update.setDisabilityType(command.getDisabilityType());
        update.setGender(command.getGender());
        update.setGestationTime(command.getGestationTime());
        update.setHasDisability(command.getHasDisability());
        update.setHeight(command.getHeight());
        update.setWeight(command.getWeight());
        update.setIsPregnant(command.getIsPregnant());

        serviceImpl.update(update);
        this.updatePatientsEventService.update(update, null);
    }
}
