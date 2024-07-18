package com.kynsof.calendar.application.command.shift.next;

import com.kynsof.calendar.domain.dto.TurnDto;
import com.kynsof.calendar.domain.dto.enumType.ETurnStatus;
import com.kynsof.calendar.domain.service.IPlaceService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.ITurnService;
import com.kynsof.calendar.infrastructure.service.socket.LocalServiceMessage;
import com.kynsof.calendar.infrastructure.service.socket.NewServiceMessage;
import com.kynsof.calendar.infrastructure.service.socket.NotificationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class NextShiftRequestCommandHandler implements ICommandHandler<NextShiftRequestCommand> {

    private final NotificationService notificationService;
    private final IPlaceService placeService;
    private final IServiceService serviceService;
    private final ITurnService turnService;
    private final IResourceService resourceService;

    public NextShiftRequestCommandHandler(NotificationService notificationService, IPlaceService placeService, IServiceService serviceService, ITurnService turnService, IResourceService resourceService) {
        this.notificationService = notificationService;
        this.placeService = placeService;
        this.serviceService = serviceService;
        this.turnService = turnService;
        this.resourceService = resourceService;
    }

    @Override
    public void handle(NextShiftRequestCommand command) {
        var place = placeService.findById(UUID.fromString(command.getLocal()));
        var service = serviceService.findByIds(UUID.fromString(command.getService()));
        var resource = resourceService.findById(UUID.fromString(command.getDoctor()));
         if(command.getLastShift() != null) {
             var lastShift = turnService.findById(UUID.fromString(command.getLastShift()));
             lastShift.setStatus(ETurnStatus.ATTENDED);
             turnService.update(lastShift);
         }
        // message to send to the shift queue
        var message = new NewServiceMessage();
        // TODO: Generate shift code
        List<TurnDto> turnDtoList = turnService.findByServiceId(service.getId(), place.getBusinessDto().getId());
        TurnDto turnDto = !turnDtoList.isEmpty() ? turnDtoList.get(0) : null;

        if (turnDto != null) {
            message.setShift(service.getCode() + "-" + String.format("%02d", turnDto.getPosition()));
            message.setService(service.getName());
            message.setLocal(place.getName());

            var block = place.getBlock();
            message.setQueueId(block.getCode());

            // message to send to the local queue
            var localMessage = new LocalServiceMessage();
            localMessage.setService(service.getName());
            localMessage.setQueueId(place.getId().toString());
            localMessage.setShift(message.getShift());
            localMessage.setPreferential(turnDto.getIsPreferential());

            // TODO: Get the information of the patient and pass it to the local queue
            localMessage.setPreferential(turnDto.getIsPreferential());
            localMessage.setIdentification(turnDto.getIdentification());

            turnDto.setLocal(place.getCode());
            turnDto.setDoctor(resource);
            turnService.update(turnDto);

            // TODO: Send the notification using integration events
            notificationService.sendNotification(message, "/api/notification/turnero");
            notificationService.sendNotification(localMessage, "/api/notification/local");
        }


    }
}
