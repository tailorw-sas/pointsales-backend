package com.kynsof.identity.application.command.replicate.objects;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.identity.infrastructure.services.kafka.producer.business.ProducerCreateBusinessEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateReplicateCommandHandler implements ICommandHandler<CreateReplicateCommand> {

    private final IBusinessService businessService;
    private final ProducerCreateBusinessEventService producerCreateBusinessEventService;

    public CreateReplicateCommandHandler(IBusinessService businessService,
                                         ProducerCreateBusinessEventService producerCreateBusinessEventService) {
        this.businessService = businessService;
        this.producerCreateBusinessEventService = producerCreateBusinessEventService;
    }

    @Override
    public void handle(CreateReplicateCommand command) {
        for (ObjectEnum object : command.getObjects()) {
            switch (object) {
                case TENANT -> {
                    for (BusinessDto businessDto : this.businessService.findAll()) {
                        this.producerCreateBusinessEventService.create(businessDto);
                    }
                }
                default -> System.out.println("Número inválido. Por favor, intenta de nuevo con un número del 1 al 7.");
            }
        }
    }
}
