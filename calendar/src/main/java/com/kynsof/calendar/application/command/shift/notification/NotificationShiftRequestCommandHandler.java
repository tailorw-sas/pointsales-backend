package com.kynsof.calendar.application.command.shift.notification;

import com.kynsof.calendar.domain.dto.PlaceDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.service.*;
import com.kynsof.calendar.infrastructure.service.socket.NewServiceMessage;
import com.kynsof.calendar.infrastructure.service.socket.NotificationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotificationShiftRequestCommandHandler implements ICommandHandler<NotificationShiftRequestCommand> {

    private final NotificationService notificationService;
    private final IPlaceService placeService;
    private final IServiceService serviceService;

    public NotificationShiftRequestCommandHandler(NotificationService notificationService, IPlaceService placeService,
                                                  IServiceService serviceService) {
        this.notificationService = notificationService;
        this.placeService = placeService;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(NotificationShiftRequestCommand command) {
        var place = placeService.findById(UUID.fromString(command.getLocal()));

        var services = serviceService.findByIds(UUID.fromString(command.getService()));
        sendNotification(services,  place, command.getShift());
    }


    private void sendNotification(ServiceDto service,  PlaceDto place, String shift) {
        // send turn TV
        var message = new NewServiceMessage();
        message.setShift(shift);
        message.setService(service.getName());
        message.setLocal(place.getName());
        var block = place.getBlock();
        message.setQueueId(block.getCode());

        // TODO: Send the notification using integration events
       notificationService.sendNotification(message, "/api/notification/turnero");
    }
}
