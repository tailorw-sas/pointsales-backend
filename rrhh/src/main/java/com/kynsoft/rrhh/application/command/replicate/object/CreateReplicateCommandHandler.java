package com.kynsoft.rrhh.application.command.replicate.object;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.DoctorKafka;
import com.kynsoft.rrhh.domain.dto.DoctorDto;
import com.kynsoft.rrhh.domain.interfaces.services.IDoctorService;
import com.kynsoft.rrhh.infrastructure.services.kafka.producer.doctor.ProducerReplicateDoctorService;
import org.springframework.stereotype.Component;

@Component
public class CreateReplicateCommandHandler implements ICommandHandler<CreateReplicateCommand> {

    private final IDoctorService doctorService;
    private final ProducerReplicateDoctorService producerReplicateDoctorService;

    public CreateReplicateCommandHandler(IDoctorService doctorService, 
                                         ProducerReplicateDoctorService producerReplicateDoctorService) {
        this.doctorService = doctorService;
        this.producerReplicateDoctorService = producerReplicateDoctorService;
    }

    @Override
    public void handle(CreateReplicateCommand command) {
        for (ObjectEnum object : command.getObjects()) {
            switch (object) {
                case DOCTOR -> {
                    for (DoctorDto doctorDto : this.doctorService.findAllToReplicate()) {
                        this.producerReplicateDoctorService.create(new DoctorKafka(
                                doctorDto.getId(), 
                                doctorDto.getIdentification(), 
                                doctorDto.getCode(), 
                                doctorDto.getEmail(), 
                                doctorDto.getName(), 
                                doctorDto.getLastName(), 
                                doctorDto.getImage(), 
                                null
                        ));
                    }
                }
                default ->
                    System.out.println("Número inválido. Por favor, intenta de nuevo con un número del 1 al 7.");
            }
        }
    }
}
