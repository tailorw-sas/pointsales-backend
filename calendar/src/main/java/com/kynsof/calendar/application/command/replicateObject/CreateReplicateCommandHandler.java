package com.kynsof.calendar.application.command.replicateObject;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.calendar.infrastructure.service.kafka.producer.service.ProducerServiceEventService;
import com.kynsof.calendar.infrastructure.service.kafka.producer.typeService.ProducerServiceTypeEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.ServiceTypeKafka;
import com.kynsof.share.core.domain.kafka.entity.Servicekafka;
import org.springframework.stereotype.Component;

@Component
public class CreateReplicateCommandHandler implements ICommandHandler<CreateReplicateCommand> {

    private final IServiceTypeService serviceTypeService;

    private final IServiceService serviceService;

    private final ProducerServiceEventService producerServiceEventService;

    private final ProducerServiceTypeEventService producerServiceTypeEventService;

    public CreateReplicateCommandHandler(IServiceTypeService serviceTypeService, IServiceService serviceService, 
                                         ProducerServiceEventService producerServiceEventService, 
                                         ProducerServiceTypeEventService producerServiceTypeEventService) {
        this.serviceTypeService = serviceTypeService;
        this.serviceService = serviceService;
        this.producerServiceEventService = producerServiceEventService;
        this.producerServiceTypeEventService = producerServiceTypeEventService;
    }

    @Override
    public void handle(CreateReplicateCommand command) {
        for (ObjectEnum object : command.getObjects()) {
            switch (object) {
                case SERVICE_TYPE -> {
                    for (ServiceTypeDto transactionStatusDto : this.serviceTypeService.findAllToReplicate()) {
                        this.producerServiceTypeEventService.create(new ServiceTypeKafka(
                                transactionStatusDto.getId(), 
                                transactionStatusDto.getName(), 
                                transactionStatusDto.getPicture(), 
                                transactionStatusDto.getStatus().name(), 
                                transactionStatusDto.getCode()
                        ));
                    }
                }
                case SERVICES -> {
                    for (ServiceDto serviceDto : this.serviceService.findAllToReplicate()) {
                        this.producerServiceEventService.create(new Servicekafka(
                                serviceDto.getId(), 
                                serviceDto.getType().getId(), 
                                serviceDto.getStatus().name(), 
                                serviceDto.getPicture(), 
                                serviceDto.getName(), 
                                serviceDto.getNormalAppointmentPrice(), 
                                serviceDto.getExpressAppointmentPrice(), 
                                serviceDto.getDescription(), 
                                serviceDto.getApplyIva(), 
                                serviceDto.getEstimatedDuration(), 
                                serviceDto.getCode(), 
                                serviceDto.isPreferFlag(), 
                                serviceDto.getMaxPriorityCount(), 
                                serviceDto.getPriorityCount(), 
                                serviceDto.getCurrentLoop(), 
                                serviceDto.getOrder()
                        ));
                    }
                }
                default ->
                    System.out.println("Número inválido. Por favor, intenta de nuevo con un número del 1 al 7.");
            }
        }
    }
}
