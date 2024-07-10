package com.kynsof.calendar.application.command.shift.next;

import com.kynsof.calendar.application.command.turn.create.RandomNumberGenerator;
import com.kynsof.calendar.domain.dto.BlockDto;
import com.kynsof.calendar.domain.service.IPlaceService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.infrastructure.service.socket.LocalServiceMessage;
import com.kynsof.calendar.infrastructure.service.socket.NewServiceMessage;
import com.kynsof.calendar.infrastructure.service.socket.NotificationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class NextShiftRequestCommandHandler implements ICommandHandler<NextShiftRequestCommand> {

    private final NotificationService notificationService;
    private final IPlaceService placeService;
    private final IServiceService serviceService;

    public NextShiftRequestCommandHandler(NotificationService notificationService, IPlaceService placeService, IServiceService serviceService) {
        this.notificationService = notificationService;
        this.placeService = placeService;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(NextShiftRequestCommand command) {
        var place = placeService.findById(UUID.fromString(command.getLocal()));
        var service = serviceService.findByIds(UUID.fromString(command.getService()));

        // message to send to the shift queue
        var message = new NewServiceMessage();
        // TODO: Generate shift code
        message.setShift(service.getCode() + "-" + String.format("%02d", RandomNumberGenerator.generateRandomNumber(0, 10)));
        message.setService(service.getName());
        message.setLocal(place.getName());

        var block = place.getBlock();
        message.setQueueId(block.getCode());

        // message to send to the local queue
        var localMessage = new LocalServiceMessage();
        localMessage.setService(service.getName());
        localMessage.setQueueId(place.getId().toString());
        localMessage.setShift(message.getShift());

        // TODO: Get the information of the patient and pass it to the local queue
        localMessage.setPreferential(false);
        localMessage.setIdentification("9999999999");

        // TODO: Send the notification using integration events
        notificationService.sendNotification(message, "/api/notification/turnero");
        notificationService.sendNotification(localMessage, "/api/notification/local");
    }
}
